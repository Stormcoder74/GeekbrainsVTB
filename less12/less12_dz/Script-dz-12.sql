DROP TABLE IF EXISTS items;

CREATE TABLE items (
id serial PRIMARY KEY,
value integer,
version serial);

INSERT INTO items (value)
VALUES (0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0);
