INSERT INTO customer(id, company_name, declared_revenue, phone, registration_date)
    VALUES(1, "Company A1", 10000000.00, "(99)11111-0000", '2023-07-21');
INSERT INTO address (city, complement, neighborhood, number, state, street, zip_code, customer_id)
    VALUES("City customer 01", "Complement 01", "Neighborhood 01", "111", "SP", "Street 01", "11111-111", 1);
INSERT INTO bank_account(id, bank, agency, account, customer_id)
    VALUES(1, "111", "1111", "1111111", 1);
INSERT INTO bank_account(id, bank, agency, account, customer_id)
    VALUES(2, "222", "2222", "2222222", 1);

INSERT INTO customer(id, company_name, declared_revenue, phone, registration_date)
    VALUES(2, "Company A2", 20000000.00, "(99)22222-0000", '2023-07-22');
INSERT INTO address (city, complement, neighborhood, number, state, street, zip_code, customer_id)
    VALUES("City customer 02", "Complement 02", "Neighborhood 02", "222 B", "SC", "Street 02", "22222-222", 2);
INSERT INTO bank_account(id, bank, agency, account, customer_id)
    VALUES(3, "333", "3333", "3333333", 2);

INSERT INTO customer(id, company_name, declared_revenue, phone, registration_date)
    VALUES(3, "Company A3", 30000000.00, "(99)33333-0000", '2023-07-23');
INSERT INTO address (city, neighborhood, number, state, street, zip_code, customer_id)
    VALUES("City customer 03", "Neighborhood 03", "333", "SC", "Street 03", "33333-333", 3);
