package com.geekbrains.teryaevs.handlers;

import com.geekbrains.teryaevs.annotations.Column;
import com.geekbrains.teryaevs.annotations.Id;
import com.geekbrains.teryaevs.annotations.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class TableMaker {
    static public void makeTables(Connection connection, List<String> classList) {
        for (String clazz : classList) {
            try {
                Class tableClass = Class.forName(clazz);
                makeTable(connection, tableClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void makeTable(Connection connection, Class tableClass) {
        Table tableAnnotation = (Table) tableClass.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            String tableName = tableAnnotation.name();
            if (tableName.equals("")) {
                tableName = tableClass.getSimpleName().toLowerCase();
            }

            Field[] fields = tableClass.getDeclaredFields();
            Map<String, String> columns = new LinkedHashMap<>();

            String primaryKey = null;

            for (Field field : fields) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName;
                if (columnAnnotation != null) {
                    columnName = columnAnnotation.name();
                    if (columnName.equals("")) {
                        columnName = field.getName().toLowerCase();
                    }

                    Class type = field.getType();
                    String columnType;
                    switch (type.getSimpleName()) {
                        case "long":
                            columnType = "int8";
                            break;
                        case "int":
                            columnType = "int4";
                            break;
                        case "String":
                            columnType = "text";
                            break;
                        default:
                            throw new RuntimeException("Unknown field type");
                    }
                    columns.put(columnName, columnType);

                    Id prKeyAnnotation = field.getAnnotation(Id.class);
                    if (prKeyAnnotation != null) {
                        if (primaryKey == null) {
                            primaryKey = columnName;
                        } else {
                            throw new RuntimeException("More than one primary key");
                            // условно допустим, что PK может быть только один
                        }
                    }
                }
            }
            createTable(connection, tableName, columns, primaryKey);
        }
    }

    private static void createTable(
            Connection connection,
            String tableName,
            Map<String, String> columns,
            String primaryKey) {

        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
        Iterator<Map.Entry<String, String>> columnIterator = columns.entrySet().iterator();

        while (columnIterator.hasNext()) {
            Map.Entry<String, String> column = columnIterator.next();
            query.append(column.getKey()).append(" ").append(column.getValue());

            if (column.getKey().equals(primaryKey))
                query.append(" PRIMARY KEY");

            if (columnIterator.hasNext())
                query.append(", ");
        }
        query.append(");");

        System.out.println(query);

        try (Statement statement = connection.createStatement()) {
            statement.execute(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
