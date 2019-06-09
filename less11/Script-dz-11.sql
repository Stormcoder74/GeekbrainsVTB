CREATE TABLE consumers (
id serial PRIMARY KEY,
name varchar(50)
);

CREATE TABLE products (
id serial PRIMARY KEY,
product_name varchar(50) UNIQUE,
price numeric check (price >= 0)
);

CREATE TABLE purchases (
id serial PRIMARY KEY,
consumers_id integer 
CONSTRAINT fk_consumers REFERENCES consumers(id) ON DELETE CASCADE,
products_id integer
CONSTRAINT fk_products REFERENCES products(id) ON DELETE CASCADE,
price_of_purchase numeric CHECK (price_of_purchase >= 0)
);

INSERT INTO consumers (name)
VALUES ('Jack'), ('Bob'), ('John'),
('Chris'), ('Susan'), ('Billi'), ('Rebeka');

INSERT INTO products (product_name, price)
VALUES ('milk', 65), ('bread', 45), ('butter', 145),
('pork', 280), ('beef', 320), ('sausage', 650), ('beer', 180),
('coffee', 170), ('sugar', 55), ('tea', 120);

INSERT INTO purchases (consumers_id, products_id, price_of_purchase)
VALUES (1, 7, 65), (1, 2, 45), (1, 9, 145), (1, 3, 145),
(2, 2, 280), (2, 6, 320), (2, 3, 650), (2, 8, 180),
(3, 1, 280), (3, 8, 320), (3, 3, 650), (3, 10, 180),
(4, 8, 280), (4, 2, 320), (4, 7, 650), (4, 9, 180),
(5, 8, 280), (5, 6, 320), (5, 4, 650), (5, 6, 180),
(6, 1, 280), (6, 5, 320), (6, 9, 650), (6, 3, 180),
(7, 5, 280), (7, 10, 320), (7, 5, 650), (7, 4, 180);