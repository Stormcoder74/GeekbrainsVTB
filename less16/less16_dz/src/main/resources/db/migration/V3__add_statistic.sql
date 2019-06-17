CREATE TABLE statistic (
	product_id bigint,
	views int,
	CONSTRAINT statistic_pk PRIMARY KEY (product_id),
	CONSTRAINT statistic_fk FOREIGN KEY (product_id) REFERENCES products(id)
);