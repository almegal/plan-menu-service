CREATE TABLE IF NOT EXISTS recipe_ingredients (
    recipe_id SERIAL NOT NULL,
    product_id SERIAL NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    ingredient_title VARCHAR(30) NOT NULL,
    PRIMARY KEY (recipe_id, product_id),
    CONSTRAINT fk_recipe_ingredient_recipes_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
    CONSTRAINT fk_recipe_ingredient_products_id FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO recipe_ingredients
    (recipe_id, product_id, amount, ingredient_title)
VALUES
    (1, 2, 3, 'Яйцо'),
    (1, 1, 2.0, 'Мука'),
    (1, 3, 1.5, 'Молоко'),
    (2, 2, 2, 'Яйцо'),
    (2, 6, 1, 'Масло'),
    (2, 7, 0.5, 'Картофель'),
    (2, 11, 0.25, 'Лук'),
    (3, 18, 1.0, 'Гречка'),
    (3, 7, 0.5, 'Картофель'),
    (3, 10, 0.25, 'Тыква'),
    (4, 8, 500, 'Курица'),
    (4, 11, 0.5, 'Лук'),
    (4, 10, 1, 'Морковь'),
    (4, 19, 0.5, 'Лапша'),
    (5, 12, 3, 'Томаты'),
    (5, 13, 2, 'Огурцы'),
    (5, 15, 0.2, 'Сметана'),
    (5, 16, 0.1, 'Сыр');