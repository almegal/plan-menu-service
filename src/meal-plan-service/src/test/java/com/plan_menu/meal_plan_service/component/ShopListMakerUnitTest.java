package com.plan_menu.meal_plan_service.component;

import com.plan_menu.meal_plan_service.dto.IngredientDto;
import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.dto.RecipeWithIngredientDto;
import com.plan_menu.meal_plan_service.dto.ShoppingListRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Юнит-тесты для класса {@link ShopListMaker}, который отвечает за создание списка покупок на основе рецепта и плана питания.
 */
public class ShopListMakerUnitTest {

    // Экземпляр тестируемого компонента.
    private final ShopListMaker shopListMaker = new ShopListMaker();

    /**
     * Метод для предоставления тестовых аргументов для параметризованных тестов.
     *
     * @return поток аргументов для тестирования метода createShoppingList.
     */
    public static Stream<Arguments> argsProvider() {
        return Stream.of(
                Arguments.of(
                        // Данные для первого теста
                        List.of(new RecipeWithIngredientDto(
                                1L,
                                "Recipe 1",
                                // Ингредиенты для первого рецепта
                                List.of(new IngredientDto("Ingredient 1", 200D), new IngredientDto("Ingredient 2", 140D)))),
                        // План на 3 порции
                        List.of(new MealPlanEntryDto(null, "Recipe 1", null, 3)),
                        // Ожидаемый список покупок
                        new ShoppingListRequestDto(2L, Map.of(
                                "Ingredient 1", 600D, // 200 * 3
                                "Ingredient 2", 420D  // 140 * 3
                        )),
                        2L // ID пользователя
                ),
                Arguments.of(
                        // Данные для второго теста
                        List.of(
                                //
                                new RecipeWithIngredientDto(
                                        4L,
                                        "Recipe 2",
                                        // Ингредиенты для второго рецепта
                                        List.of(
                                                new IngredientDto("Ingredient 2", 1000D),
                                                new IngredientDto("Ingredient 3", 293824.00D),
                                                new IngredientDto("Ingredient 4", 1000.78D),
                                                new IngredientDto("Ingredient 5", 0.10D))),
                                //
                                new RecipeWithIngredientDto(
                                        4L,
                                        "Recipe 3",
                                        // Ингредиенты для второго рецепта
                                        List.of(
                                                new IngredientDto("Ingredient 6", 1000D),
                                                new IngredientDto("Ingredient 7", 293824.00D),
                                                new IngredientDto("Ingredient 8", 1000.78D),
                                                new IngredientDto("Ingredient 9", 0.10D)))
                        ),
                        // План на 5 порций
                        List.of(
                                new MealPlanEntryDto(null, "Recipe 2", null, 5),
                                new MealPlanEntryDto(null, "Recipe 3", null, 2)
                        ),
                        // Ожидаемый список покупок
                        new ShoppingListRequestDto(59L, Map.of(
                                "Ingredient 2", 5000.0D,   //
                                "Ingredient 3", 1469120.0D, //
                                "Ingredient 4", 5003.9D,    //
                                "Ingredient 5", 0.5D,       //
                                "Ingredient 6", 2000.0D,   //
                                "Ingredient 7", 587648.0D, //
                                "Ingredient 8", 2001.56D,    //
                                "Ingredient 9", 0.20D            //
                        )),
                        59L // ID пользователя
                )
        );
    }

    /**
     * Тестирует корректность создания списка покупок на основе данных рецепта и плана питания.
     *
     * @param recipeWithIngredientDto данные рецепта с ингредиентами
     * @param mealPlanEntryDto        данные записи плана питания (включает количество порций)
     * @param expected                ожидаемый результат - объект ShoppingListRequestDto
     * @param userId                  идентификатор пользователя
     */
    @DisplayName("Корректно высчитывает список для заказа продуктов")
    @ParameterizedTest
    @MethodSource("argsProvider")
    public void shouldReturnCorrectListForMakeOrder(List<RecipeWithIngredientDto> recipeWithIngredientDto,
                                                    List<MealPlanEntryDto> mealPlanEntryDto,
                                                    ShoppingListRequestDto expected,
                                                    Long userId) {

        // Создаем список покупок на основе входных данных
        ShoppingListRequestDto actual = shopListMaker.createShoppingList(userId, recipeWithIngredientDto, mealPlanEntryDto);
        // Проверяем, что результат выполнения метода соответствует ожидаемому результату
        assertEquals(expected, actual);
    }
}
