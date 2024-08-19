CREATE TABLE IF NOT EXISTS categories (
    category_id SERIAL PRIMARY KEY,
    category_title VARCHAR(100) NOT NULL
);
INSERT INTO categories
    (category_title)
VALUES
    ('Завтрак'),
    ('Обед'),
    ('Ужин'),
    ('Салаты'),
    ('Супы'),
    ('Закуски'),
    ('Выпечка'),
    ('Десерты'),
    ('Каши'),
    ('Напитки'),
    ('Вегетарианские'),
    ('Диетические'),
    ('Постные'),
    ('Праздничные'),
    ('Быстрые рецепты');