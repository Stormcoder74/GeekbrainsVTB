CREATE TABLE students (
id serial PRIMARY KEY,
name varchar(50),
passport_series char(4),
CHECK (passport_series ~ '^[0-9]*$'),
passport_number char(6),
CHECK (passport_number ~ '^[0-9]*$'),
CONSTRAINT unique_passport UNIQUE (passport_series, passport_number)
);

CREATE TABLE subjects (
id serial PRIMARY KEY,
title varchar(50) UNIQUE
);

CREATE TABLE progress (
students_id integer
CONSTRAINT fk_students REFERENCES students(id) ON DELETE CASCADE,
subjects_id integer
CONSTRAINT fk_subjects REFERENCES subjects(id),
mark integer CHECK (mark >= 2 AND mark <= 5),
PRIMARY KEY (students_id, subjects_id)
);

INSERT INTO students (name, passport_series, passport_number)
VALUES ('Jack', '9301', '516796'),
('Bob', '3587', '835710'),
('John', '3587', '092584'),
('Chris', '3494', '092584'),
('Susan', '8392', '937502'),
('Billi', '5799', '683030'),
('Rebeka', '8403', '048573');

INSERT INTO subjects (title)
VALUES ('Java'),
('Data bases'),
('Algoritms'),
('Spring'),
('AI'),
('Android'),
('Operation systems');

INSERT INTO progress
VALUES (1, 3, 4),
(1, 2, 3),
(1, 4, 5),
(1, 7, 3),
(2, 5, 2),
(2, 3, 5),
(2, 2, 3),
(2, 1, 3),
(3, 6, 4),
(3, 3, 2),
(4, 1, 4),
(4, 7, 5),
(5, 3, 3),
(5, 1, 5),
(6, 7, 4),
(7, 5, 3),
(7, 3, 2),
(7, 2, 5);

CREATE VIEW progress_report AS SELECT name, title, mark FROM progress
INNER JOIN students ON students_id = students.id
INNER JOIN subjects ON subjects_id = subjects.id;

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