CREATE TABLE IF NOT EXISTS recipe_ingredients (
    recipe_id SERIAL NOT NULL,
    product_id SERIAL NOT NULL,
    amount DECIMAL(10,2),
    PRIMARY KEY (recipe_id, product_id),
    CONSTRAINT fk_recipe_ingredient_recipes_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
    CONSTRAINT fk_recipe_ingredient_products_id FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO recipe_ingredients
    (recipe_id, product_id, amount)
VALUES
    (1, 2, 3), -- Блины: яйца 3 шт
    (1, 1, 2.0), -- Блины: мука 2 ст.
    (1, 3, 1.5), -- Блины: молоко 1.5 ст.
    (2, 2, 2), -- Омлет: яйца 2 шт
    (2, 6, 1), -- Омлет: масло 1 ст.л.
    (2, 7, 0.5), -- Омлет: картофель 0.5 шт
    (2, 11, 0.25), -- Омлет: лук 0.25 шт
    (3, 18, 1.0), -- Гречневая каша: гречка 1 ст.
    (3, 7, 0.5), -- Гречневая каша: картофель 0.5 шт
    (3, 10, 0.25), -- Гречневая каша: тыква 0.25 шт
    (4, 8, 500), -- Куриный суп: курица 500 г
    (4, 11, 0.5), -- Куриный суп: лук 0.5 шт
    (4, 10, 1), -- Куриный суп: морковь 1 шт
    (4, 19, 0.5), -- Куриный суп: лапша 0.5 пачки
    (5, 12, 3), -- Греческий салат: томаты 3 шт
    (5, 13, 2), -- Греческий салат: огурцы 2 шт
    (5, 15, 0.2), -- Греческий салат: сметана 0.2 ст.
    (5, 16, 0.1); -- Греческий салат: сыр 0.1 кг