CREATE TABLE customer (
	id BIGINT not null auto_increment(5) unique,
	first_name varchar(100) not null,
	last_name varchar(100) not null
);
CREATE TABLE MOBILE_NUMBER (
    ID BIGINT auto_increment(6) unique,
    CUSTOMER_ID BIGINT NOT NULL,
    NUMBER VARCHAR(20) NOT NULL
);

