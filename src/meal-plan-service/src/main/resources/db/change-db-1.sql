-- liquibase formatted sql

-- changeset alexey:1
CREATE TABLE meal_plan (
    meal_plan_id SERIAL PRIMARY KEY,  -- Идентификатор плана меню
    user_id INT NOT NULL              -- Идентификатор пользователя
);
CREATE TABLE meal_plan_entry (
    entry_id SERIAL PRIMARY KEY,         -- Идентификатор записи в плане
    recipe_title VARCHAR(150) NOT NUll,  -- Наименование рецепта
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
