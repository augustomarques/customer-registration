CREATE TABLE customer (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(150),
    phone VARCHAR(14),
    declared_revenue DECIMAL(38,2),
    registration_date DATE,
    PRIMARY KEY (id)
);

CREATE index idx_customer_sort
    ON customer (registration_date DESC);