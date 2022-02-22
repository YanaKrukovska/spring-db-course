INSERT INTO storages(id, address)
VALUES (1, 'Kyiv, Peremohy St 1'),
       (2, 'Kyiv, Bandery St 123');

INSERT INTO groups(id, name, description)
VALUES (1, 'Clothes', 'Any clothes'),
       (2, 'Dairy', 'Products containing milk');

INSERT INTO products(id, name, description, manufacturer, price, group_id)
VALUES (1, 'Butter', '72% Butter', 'Roshen', 49.86, 2),
       (2, 'Chocolate Butter', 'Chocolate taste butter', 'Roshen', 53.20, 2),
       (3, 'Shorts', 'Short shorts', 'Collins', 1000, 1);

INSERT INTO storage_products(id, amount, product_id, storage_id)
VALUES (1, 40, 1, 1),
       (2, 18, 1, 2),
       (3, 10, 2, 1),
       (4, 2, 2, 2),
       (5, 100, 3, 1);

INSERT INTO bookings(ID, AMOUNT, BOOKING_DATE, UNTIL_DATE, STORAGE_ID, STORAGE_PRODUCT_ID)
VALUES (1, 2, '2022-02-01 16:01:45.000', '2022-02-08 16:01:45.000', 1, 1);

INSERT INTO orders(ID, CUSTOMER, STATUS)
VALUES (1, 'John Doe', 'PROCESSED'),
       (2, 'Jane Doe', 'IN_PROGRESS'),
       (3, 'John Doe', 'NEW');

INSERT INTO details (ID, AMOUNT, STORAGE_PRODUCT_ID)
VALUES (1, 5, 3),
       (2, 1, 5),
       (3, 1, 1),
       (4, 2, 2),
       (5, 1, 1),
       (6, 1, 2);

INSERT INTO orders_order_details(ORDERS_ID, ORDER_DETAILS_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (3, 5),
       (3, 6);

