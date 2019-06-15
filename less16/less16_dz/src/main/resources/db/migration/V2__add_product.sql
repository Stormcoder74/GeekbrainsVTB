CREATE TABLE products (
    id serial PRIMARY KEY,
    title VARCHAR(100),
    price NUMERIC
);

INSERT INTO products (title, price)
VALUES ('Bread', 40), ('Bread', 40), ('Bread', 40), ('Bread', 40), ('Bread', 40),
('Milk', 40),    ('Milk', 40),    ('Milk', 40),    ('Milk', 40),    ('Milk', 40),
('Meat', 250),   ('Meat', 250),   ('Meat', 250),   ('Meat', 250),   ('Meat', 250),
('Cheese', 540), ('Cheese', 540), ('Cheese', 540), ('Cheese', 540), ('Cheese', 540),
('Tea', 120),    ('Tea', 120),    ('Tea', 120),    ('Tea', 120),    ('Tea', 120);
