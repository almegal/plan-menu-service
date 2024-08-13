-- liquibase formatted sql

-- changeset alexey:1
CREATE TABLE MealPlan (
    meal_plan_id SERIAL PRIMARY KEY,  -- Идентификатор плана меню
    user_id INT NOT NULL              -- Идентификатор пользователя
);
CREATE TABLE MealPlanEntry (
    entry_id SERIAL PRIMARY KEY,         -- Идентификатор записи в плане
    meal_plan_id INT NOT NULL,           -- Ссылка на MealPlan
    recipe_id INT NOT NULL,              -- Идентификатор рецепта из другой БД/микросервиса
    week_day VARCHAR(10) NOT NULL,       -- День недели (например, 'Monday', 'Tuesday' и т.д.)
    servings INT NOT NULL,               -- Количество порций или людей


    CONSTRAINT fk_meal_plan
        FOREIGN KEY (meal_plan_id)
        REFERENCES MealPlan(meal_plan_id)
        ON DELETE CASCADE
);
CREATE INDEX idx_meal_plan_user_id ON MealPlan(user_id);
CREATE INDEX idx_meal_plan_entry_plan_id ON MealPlanEntry(meal_plan_id);
