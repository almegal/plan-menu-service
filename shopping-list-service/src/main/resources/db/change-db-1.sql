-- liquibase formatted sql

-- Создание базы данных
CREATE DATABASE shopp_service;

-- Использование созданной базы данных
\c shopp_service;

-- Создание таблицы products
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    weight_per_pack DOUBLE PRECISION,
    description_short TEXT,
    count_on_storage INTEGER,
    unit VARCHAR(50),
    status VARCHAR(50)
);

-- Создание таблицы shopping_lists
CREATE TABLE shopping_lists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_date TIMESTAMP,
    status VARCHAR(50),
    collection_status VARCHAR(50),
    readiness_status VARCHAR(50),
    user_id BIGINT,
    meal_plan_id BIGINT
);

-- Создание таблицы shopping_list_items
CREATE TABLE shopping_list_items (
    id SERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity INTEGER,
    unit VARCHAR(50),
    shopping_list_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (shopping_list_id) REFERENCES shopping_lists(id)
);
-- Вставка 15 продуктов
INSERT INTO products (title, weight_per_pack, description_short, count_on_storage, unit, status) VALUES
('Молоко', 1.0, 'Свежее молоко', 100, 'LITER', 'AVAILABLE'),
('Хлеб', 0.5, 'Пшеничный хлеб', 50, 'KILOGRAM', 'AVAILABLE'),
('Яйца', 0.6, 'Яйца фермерские', 200, 'PIECE', 'AVAILABLE'),
('Масло', 0.25, 'Солёное масло', 30, 'KILOGRAM', 'AVAILABLE'),
('Сыр', 0.2, 'Сыр чеддер', 40, 'KILOGRAM', 'AVAILABLE'),
('Яблоки', 1.0, 'Яблоки голден', 150, 'KILOGRAM', 'AVAILABLE'),
('Бананы', 1.0, 'Свежие бананы', 120, 'KILOGRAM', 'AVAILABLE'),
('Апельсины', 1.0, 'Апельсины навел', 130, 'KILOGRAM', 'AVAILABLE'),
('Куриная грудка', 1.0, 'Куриное филе', 80, 'KILOGRAM', 'AVAILABLE'),
('Говядина', 0.5, 'Говяжий стейк', 60, 'KILOGRAM', 'AVAILABLE'),
('Свинина', 0.5, 'Свиная отбивная', 70, 'KILOGRAM', 'AVAILABLE'),
('Рыба', 0.5, 'Свежие рыбные филе', 50, 'KILOGRAM', 'AVAILABLE'),
('Рис', 5.0, 'Рис басмати', 200, 'KILOGRAM', 'AVAILABLE'),
('Макароны', 0.5, 'Макароны спагетти', 100, 'KILOGRAM', 'AVAILABLE'),
('Помидоры', 1.0, 'Свежие помидоры', 140, 'KILOGRAM', 'AVAILABLE');

-- Вставка 15 списков покупок
INSERT INTO shopping_lists (name, description, created_date, status, collection_status, readiness_status, user_id, meal_plan_id) VALUES
('Shopping List 1', 'Weekly shopping', '2024-08-22 10:00:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 1, 1),
('Shopping List 2', 'Weekend shopping', '2024-08-20 15:30:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 2, 2),
('Shopping List 3', 'Party shopping', '2024-08-18 12:45:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 3, 3),
('Shopping List 4', 'Guest shopping', '2024-08-16 09:15:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 4, 4),
('Shopping List 5', 'Barbecue shopping', '2024-08-14 14:20:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 5, 5),
('Shopping List 6', 'Easter shopping', '2024-08-12 11:30:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 6, 6),
('Shopping List 7', 'Birthday shopping', '2024-08-10 16:40:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 7, 7),
('Shopping List 8', 'New Year shopping', '2024-08-08 13:50:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 8, 8),
('Shopping List 9', 'Mother''s Day shopping', '2024-08-06 10:05:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 9, 9),
('Shopping List 10', 'Father''s Day shopping', '2024-08-04 15:10:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 10, 10),
('Shopping List 11', 'Teacher''s Day shopping', '2024-08-02 12:15:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 11, 11),
('Shopping List 12', 'Defender of the Fatherland Day shopping', '2024-08-01 09:20:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 12, 12),
('Shopping List 13', 'Knowledge Day shopping', '2024-07-30 14:25:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 13, 13),
('Shopping List 14', 'Old People''s Day shopping', '2024-07-28 11:35:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 14, 14),
('Shopping List 15', 'Youth Day shopping', '2024-07-26 16:45:00', 'ACTIVE', 'NOT_STARTED', 'NOT_READY', 15, 15);

-- Вставка 15 элементов списка покупок
INSERT INTO shopping_list_items (product_id, quantity, unit, shopping_list_id) VALUES
(1, 2, 'LITER', 1),
(2, 1, 'KILOGRAM', 1),
(3, 12, 'PIECE', 1),
(4, 1, 'KILOGRAM', 1),
(5, 1, 'KILOGRAM', 1),
(6, 2, 'KILOGRAM', 2),
(7, 1, 'KILOGRAM', 2),
(8, 1, 'KILOGRAM', 2),
(9, 1, 'KILOGRAM', 2),
(10, 1, 'KILOGRAM', 2),
(11, 1, 'KILOGRAM', 3),
(12, 1, 'KILOGRAM', 3),
(13, 1, 'KILOGRAM', 3),
(14, 1, 'KILOGRAM', 3),
(15, 1, 'KILOGRAM', 3);