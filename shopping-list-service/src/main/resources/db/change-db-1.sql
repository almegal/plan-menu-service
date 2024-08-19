-- Создание таблицы products
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    weight_per_pack DOUBLE PRECISION,
    description_short VARCHAR(255),
    count_on_storage INT NOT NULL,
    unit VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL
);

-- Создание таблицы shopping_lists
CREATE TABLE shopping_lists (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    created_date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    collection_status VARCHAR(50),
    readiness_status VARCHAR(50),
    user_id BIGINT NOT NULL
);

-- Создание таблицы shopping_list_items
CREATE TABLE shopping_list_items (
    id BIGSERIAL PRIMARY KEY,
    shopping_list_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit VARCHAR(50),
    FOREIGN KEY (shopping_list_id) REFERENCES shopping_lists(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Вставка начальных данных в таблицу products
INSERT INTO products (title, weight_per_pack, description_short, count_on_storage, unit, status) VALUES
('Milk', 1.0, 'Fresh milk', 100, 'LITER', 'AVAILABLE'),
('Bread', 0.5, 'Whole wheat bread', 50, 'PIECE', 'AVAILABLE'),
('Eggs', 0.6, 'Farm fresh eggs', 200, 'PIECE', 'AVAILABLE'),
('Butter', 0.25, 'Salted butter', 30, 'KILOGRAM', 'AVAILABLE'),
('Sugar', 1.0, 'Granulated sugar', 150, 'KILOGRAM', 'AVAILABLE'),
('Salt', 0.5, 'Table salt', 200, 'KILOGRAM', 'AVAILABLE'),
('Flour', 1.0, 'All-purpose flour', 100, 'KILOGRAM', 'AVAILABLE'),
('Rice', 1.0, 'Long grain rice', 80, 'KILOGRAM', 'AVAILABLE'),
('Olive Oil', 0.75, 'Extra virgin olive oil', 50, 'LITER', 'AVAILABLE'),
('Cheese', 0.2, 'Cheddar cheese', 40, 'KILOGRAM', 'AVAILABLE');

-- Вставка начальных данных в таблицу shopping_lists
INSERT INTO shopping_lists (name, description, created_date, status, collection_status, readiness_status, user_id) VALUES
('Weekly Groceries', 'Groceries for the week', '2023-10-01 10:00:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 1),
('Party Supplies', 'Supplies for the party', '2023-10-05 15:00:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 2);

-- Вставка начальных данных в таблицу shopping_list_items
INSERT INTO shopping_list_items (shopping_list_id, product_id, quantity, unit) VALUES
(1, 1, 2, 'LITER'),
(1, 2, 1, 'PIECE'),
(1, 3, 12, 'PIECE'), -- 12 eggs in a dozen
(1, 4, 1, 'KILOGRAM'),
(1, 5, 1, 'KILOGRAM'),
(1, 6, 1, 'KILOGRAM'),
(2, 2, 3, 'PIECE'),
(2, 4, 1, 'KILOGRAM'),
(2, 7, 2, 'KILOGRAM'),
(2, 8, 1, 'KILOGRAM'),
(2, 9, 1, 'LITER'),
(2, 10, 1, 'KILOGRAM');