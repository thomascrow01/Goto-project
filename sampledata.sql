--MEMBERS--
INSERT INTO memberentity (id, address, email, mobile, name) VALUES (20, "123 Street st.", "test1@email.com", "0111-111-111", "John Smith");
INSERT INTO memberentity (id, address, email, mobile, name) VALUES (21, "456 Road st.", "test2@email.com", "0112-222-222", "Steve Smith");
INSERT INTO memberentity (id, address, email, mobile, name) VALUES (22, "890 Street st.", "test3@email.com", "0113-333-3", "Larry Smith");

--PRODUCTS--
INSERT INTO productentity (id, description, name, price) VALUES (30, "A vegetable", "Cauliflour", 5.5);
INSERT INTO productentity (id, description, name, price) VALUES (31, "A fruit", "Apple", 4.5);
INSERT INTO productentity (id, description, name, price) VALUES (32, "A spice", "Cinnamon", 3.2);

--INVOICES--
INSERT INTO invoiceentity (id, memberID, productID, date) VALUES (30, 20, 31, 2015-01-01);
INSERT INTO invoiceentity (id, memberID, productID, date) VALUES (30, 22, 32, 2015-06-03);
INSERT INTO invoiceentity (id, memberID, productID, date) VALUES (30, 20, 32, 2015-04-05);

--INVOICE PRODUCT ENTITY--
INSERT INTO invoiceproductentity (id, invoiceID, productID, quantity) VALUES (30, 12345, 30, 15);
INSERT INTO invoiceproductentity (id, invoiceID, productID, quantity) VALUES (30, 12346, 30, 20);
INSERT INTO invoiceproductentity (id, invoiceID, productID, quantity) VALUES (30, 12347, 32, 8);