CREATE TABLE IF NOT EXISTS recipes(
    recipe_id SERIAL PRIMARY KEY,
    recipe_title VARCHAR(100) NOT NULL,
    time_for_cook INT NOT NULL,
    category_id INTEGER,
    CONSTRAINT fk_recipes_categories_id FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
INSERT INTO recipes
    (recipe_title, time_for_cook, category_id)
VALUES
    ('Классические блины', 30, 1),
    ('Омлет с овощами', 20, 1),
    ('Гречневая каша с тыквой', 45, 9),
    ('Куриный суп с лапшой', 60, 5),
    ('Греческий салат', 15, 4),
    ('Запеченная форель с овощами', 50, 2),
    ('Спагетти болоньезе', 40, 3),
    ('Брокколи с чесночным соусом', 25, 11),
    ('Яблочный пирог', 90, 7),
    ('Смузи из ягод', 10, 10),
    ('Куриные крылышки BBQ', 50, 6),
    ('Овсяная каша с ягодами', 20, 9),
    ('Томатный суп-пюре', 35, 5),
    ('Цезарь с курицей', 25, 4),
    ('Шоколадный фондан', 45, 8),
    ('Фаршированные шампиньоны', 30, 6),
    ('Картофельное пюре', 25, 2),
    ('Фруктовый салат', 20, 4),
    ('Куриная лапша', 40, 5),
    ('Безглютеновый хлеб', 120, 7);