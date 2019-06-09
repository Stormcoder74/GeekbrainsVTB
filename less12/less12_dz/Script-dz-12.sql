create table items (
id serial primary key,
value integer);

truncate items restart identity;

insert into items (value)
values (0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0),
(0), (0), (0), (0), (0);
