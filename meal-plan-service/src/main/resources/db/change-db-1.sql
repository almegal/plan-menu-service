-- liquibase formatted sql

-- changeset alexey:1
CREATE TABLE meal_plan (
    meal_plan_id SERIAL PRIMARY KEY,  -- Идентификатор плана меню
    user_id INT NOT NULL              -- Идентификатор пользователя
);

CREATE TABLE meal_plan_entry (
    entry_id SERIAL PRIMARY KEY,         -- Идентификатор записи в плане
    recipe_title VARCHAR(150) NOT NULL,  -- Наименование рецепта
    meal_plan_id INT NOT NULL,           -- Ссылка на MealPlan
    recipe_id INT NOT NULL,              -- Идентификатор рецепта из другой БД/микросервиса
    week_day VARCHAR(15) NOT NULL,       -- День недели (например, 'Monday', 'Tuesday' и т.д.)
    servings INT NOT NULL,               -- Количество порций или людей

    CONSTRAINT fk_meal_plan
        FOREIGN KEY (meal_plan_id)
        REFERENCES meal_plan(meal_plan_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_meal_plan_user_id ON meal_plan(user_id);
CREATE INDEX idx_meal_plan_entry_plan_id ON meal_plan_entry(meal_plan_id);

-- Добавление тестовых данных

-- Пользователь 1
INSERT INTO meal_plan (user_id) VALUES (1);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (1, 'Recipe 1', 101, 'Monday', 2);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (1, 'Recipe 2', 102, 'Tuesday', 4);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (1, 'Recipe 3', 103, 'Wednesday', 3);

-- Пользователь 2
INSERT INTO meal_plan (user_id) VALUES (2);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (2, 'Recipe 4', 104, 'Monday', 2);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (2, 'Recipe 5', 105, 'Tuesday', 4);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (2, 'Recipe 6', 106, 'Wednesday', 3);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (2, 'Recipe 7', 107, 'Thursday', 5);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (2, 'Recipe 8', 108, 'Friday', 2);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (2, 'Recipe 9', 109, 'Saturday', 3);

-- Пользователь 3
INSERT INTO meal_plan (user_id) VALUES (3);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (3, 'Recipe 10', 110, 'Monday', 4);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (3, 'Recipe 11', 111, 'Tuesday', 3);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (3, 'Recipe 12', 112, 'Wednesday', 2);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (3, 'Recipe 13', 113, 'Thursday', 6);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (3, 'Recipe 14', 114, 'Friday', 1);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (3, 'Recipe 15', 115, 'Saturday', 5);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (3, 'Recipe 16', 116, 'Sunday', 2);

-- Пользователь 4
INSERT INTO meal_plan (user_id) VALUES (4);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (4, 'Recipe 17', 117, 'Monday', 3);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (4, 'Recipe 18', 118, 'Tuesday', 2);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (4, 'Recipe 19', 119, 'Wednesday', 4);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (4, 'Recipe 20', 120, 'Thursday', 3);
INSERT INTO meal_plan_entry (meal_plan_id, recipe_title, recipe_id, week_day, servings) VALUES (4, 'Recipe 21', 121, 'Friday', 5);
