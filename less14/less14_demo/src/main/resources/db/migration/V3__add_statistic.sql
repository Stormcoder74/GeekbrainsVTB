CREATE TABLE statistic (
  id serial,
	product_id bigint,
	views int,
	CONSTRAINT statistic_pk PRIMARY KEY (id),
	CONSTRAINT statistic_fk FOREIGN KEY (product_id) REFERENCES products(id)
);