INSERT INTO storages(id, address)
VALUES (1, 'Kyiv, Peremohy St 1'),
       (2, 'Kyiv, Bandery St 123');

INSERT INTO groups(id, name, description)
VALUES (1, 'Food', 'Ordinary food'),
       (2, 'Dairy', 'Products containing milk');

INSERT INTO products(id, name, description, manufacturer, price, group_id)
VALUES (1, 'Butter', '72% Butter', 'Roshen', 49.86, 2);

INSERT INTO storage_products(id, amount, product_id, storage_id)
VALUES (1, 4, 1, 1),
       (2, 18, 1, 2);
