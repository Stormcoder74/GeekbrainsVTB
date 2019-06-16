CREATE TABLE products (
    id serial PRIMARY KEY,
    title VARCHAR(100),
    price NUMERIC(8, 2)
);

INSERT INTO products (title, price)
VALUES ('Bread', 45), ('Bread', 47), ('Bread', 50), ('Bread', 55), ('Bread', 52),
('Milk', 60),    ('Milk', 70),    ('Milk', 50),    ('Milk', 45),    ('Milk', 55),
('Meat', 260),   ('Meat', 270),   ('Meat', 290),   ('Meat', 275),   ('Meat', 265),
('Cheese', 640), ('Cheese', 650), ('Cheese', 440), ('Cheese', 590), ('Cheese', 750),
('Tea', 130),    ('Tea', 160),    ('Tea', 140),    ('Tea', 145),    ('Tea', 170);
