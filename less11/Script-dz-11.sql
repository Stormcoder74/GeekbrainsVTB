CREATE TABLE consumers (
id serial PRIMARY KEY,
name varchar(50)
);

CREATE TABLE products (
id serial PRIMARY KEY,
product_name varchar(50) UNIQUE,
price integer check (price >= 0)
);

CREATE TABLE purchases (
id serial PRIMARY KEY,
consumers_id integer 
CONSTRAINT fk_consumers REFERENCES consumers(id) ON DELETE CASCADE,
products_id integer
CONSTRAINT fk_products REFERENCES products(id),
price_of_purchase integer CHECK (price_of_purchase >= 0)
);

INSERT INTO consumers (name)
VALUES ('Jack'), ('Bob'), ('John'),
('Chris'), ('Susan'), ('Billi'), ('Rebeka');

INSERT INTO products (product_name, price)
VALUES ('milk', 65), ('bread', 45), ('butter', 145),
('pork', 280), ('beef', 320), ('sausage', 650), ('beer', 180),
('coffee', 170), ('sugar', 55), ('tea', 120);

CREATE VIEW purchases_report AS SELECT name, product_name, price_of_purchase FROM purchases
INNER JOIN consumers ON consumers_id = consumers.id
INNER JOIN products ON products_id = products.id;










SELECT * FROM progress_report WHERE mark > 3;

SELECT avg (mark) FROM progress
INNER JOIN subjects ON subjects_id = id
WHERE title = 'Algoritms';

SELECT avg (mark) FROM progress
INNER JOIN students ON students_id = id
WHERE name = 'Jack';

SELECT title, count(*) FROM progress_report
GROUP BY title
ORDER BY count(*) DESC
LIMIT 3;