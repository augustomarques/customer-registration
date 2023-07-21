CREATE TABLE address (
    customer_id BIGINT NOT NULL,
    zip_code VARCHAR(9),
    state VARCHAR(50),
    city VARCHAR(50),
    neighborhood VARCHAR(50),
    street VARCHAR(150),
    number VARCHAR(10),
    complement VARCHAR(200),
    PRIMARY KEY (customer_id)
);

ALTER TABLE address
   ADD CONSTRAINT FK_address_customer_id
   FOREIGN KEY (customer_id)
   REFERENCES customer (id);