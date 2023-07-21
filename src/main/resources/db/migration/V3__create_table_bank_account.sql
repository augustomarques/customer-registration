CREATE TABLE bank_account (
    id BIGINT NOT NULL AUTO_INCREMENT,
    bank VARCHAR(3),
    agency VARCHAR(4),
    account VARCHAR(10),
    customer_id BIGINT,
    PRIMARY KEY (id)
);

ALTER TABLE bank_account
   ADD CONSTRAINT FK_bank_account_customer_id
   FOREIGN KEY (customer_id)
   REFERENCES customer (id)
   ON DELETE CASCADE;