
INSERT INTO MemberEntity (id, address, email, mobile, name) VALUES (20, "123 Street st.", "test1@email.com", "0111-111-111", "John Smith");
INSERT INTO MemberEntity (id, address, email, mobile, name) VALUES (21, "456 Road st.", "test2@email.com", "0112-222-222", "Steve Smith");
INSERT INTO MemberEntity (id, address, email, mobile, name) VALUES (22, "890 Street st.", "test3@email.com", "0113-333-3", "Larry Smith");


INSERT INTO ProductEntity (id, description, name, price) VALUES (30, "A vegetable", "Cauliflour", 5.5);
INSERT INTO ProductEntity (id, description, name, price) VALUES (31, "A fruit", "Apple", 4.5);
INSERT INTO ProductEntity (id, description, name, price) VALUES (32, "A spice", "Cinnamon", 3.2);


INSERT INTO InvoiceEntity (id, memberID, date) VALUES (41, 21, "2015-01-01");
INSERT INTO InvoiceEntity (id, memberID, date) VALUES (42, 22, "2015-06-03");
INSERT INTO InvoiceEntity (id, memberID, date) VALUES (43, 21, "2015-04-05");


INSERT INTO InvoiceProductEntity (id, invoiceID, productID, quantity) VALUES (51, 41, 31, 15);
INSERT INTO InvoiceProductEntity (id, invoiceID, productID, quantity) VALUES (52, 42, 31, 20);
INSERT INTO InvoiceProductEntity (id, invoiceID, productID, quantity) VALUES (53, 43, 32, 8);