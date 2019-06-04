package com.geekbrains.teryaevs.handlers;

import com.geekbrains.teryaevs.annotations.Column;
import com.geekbrains.teryaevs.annotations.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Repository {
    public void save(Connection connection, Object object) {
        Class objectClass = object.getClass();
        Table tableAnnotation = (Table) objectClass.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            String tableName = tableAnnotation.name();
            if (tableName.equals("")) {
                tableName = objectClass.getSimpleName().toLowerCase();
            }

            Field[] fields = objectClass.getDeclaredFields();
            Map<String, Object> columns = new LinkedHashMap<>();

            for (Field field : fields) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation != null) {
                    String columnName = columnAnnotation.name();
                    if (columnName.equals("")) {
                        columnName = field.getName().toLowerCase();
                    }

                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    Object value = null;
                    try {
                        value = field.get(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    columns.put(columnName, value);
                }
            }
            insert(connection, tableName, columns);
        }
    }

    private void insert(
            Connection connection,
            String tableName,
            Map<String, Object> columns
    ) {
        Iterator<Map.Entry<String, Object>> columnIterator = columns.entrySet().iterator();

        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder("VALUES (");

        while (columnIterator.hasNext()) {
            Map.Entry<String, Object> column = columnIterator.next();
            query.append(column.getKey());

            if (column.getValue() instanceof String)
                values.append("'").append(column.getValue()).append("'");
            else
                values.append(column.getValue());

            if (columnIterator.hasNext()) {
                query.append(", ");
                values.append(", ");
            }
        }
        query.append(") ");
        values.append(");");
        query.append(values);

        System.out.println(query);

        try (Statement statement = connection.createStatement()) {
            statement.execute(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
