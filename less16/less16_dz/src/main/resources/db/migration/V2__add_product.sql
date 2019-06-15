CREATE TABLE products (
    id serial PRIMARY KEY,
    title VARCHAR(100),
    price NUMERIC
);

INSERT INTO products (title, price)
VALUES ('Bread', 40), ('Milk', 80);
