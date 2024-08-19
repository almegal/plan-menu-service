CREATE TABLE IF NOT EXISTS recipe_ingredients (
    recipe_id SERIAL NOT NULL,
    product_id SERIAL NOT NULL,
    amount DECIMAL(10,2),
    ingredient_title VARCHAR(30),
    PRIMARY KEY (recipe_id, product_id),
    CONSTRAINT fk_recipe_ingredient_recipes_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
    CONSTRAINT fk_recipe_ingredient_products_id FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO recipe_ingredients
    (recipe_id, product_id, amount, ingredient_title)
VALUES
    (1, 2, 3, 'Яйцо'), -- Блины: яйца 3 шт
    (1, 1, 2.0, 'Мука'), -- Блины: мука 2 ст.
    (1, 3, 1.5, 'Молоко'), -- Блины: молоко 1.5 ст.
    (2, 2, 2, 'Яйцо'), -- Омлет: яйца 2 шт
    (2, 6, 1, 'Масло'), -- Омлет: масло 1 ст.л.
    (2, 7, 0.5, 'Картофель'), -- Омлет: картофель 0.5 шт
    (2, 11, 0.25, 'Лук'), -- Омлет: лук 0.25 шт
    (3, 18, 1.0, 'Гречка'), -- Гречневая каша: гречка 1 ст.
    (3, 7, 0.5, 'Картофель'), -- Гречневая каша: картофель 0.5 шт
    (3, 10, 0.25, 'Тыква'), -- Гречневая каша: тыква 0.25 шт
    (4, 8, 500, 'Курица'), -- Куриный суп: курица 500 г
    (4, 11, 0.5, 'Лук'), -- Куриный суп: лук 0.5 шт
    (4, 10, 1, 'Морковь'), -- Куриный суп: морковь 1 шт
    (4, 19, 0.5, 'Лапша'), -- Куриный суп: лапша 0.5 пачки
    (5, 12, 3, 'Томаты'), -- Греческий салат: томаты 3 шт
    (5, 13, 2, 'Огурцы'), -- Греческий салат: огурцы 2 шт
    (5, 15, 0.2, 'Сметана'), -- Греческий салат: сметана 0.2 ст.
    (5, 16, 0.1, 'Сыр'); -- Греческий салат: сыр 0.1 кг