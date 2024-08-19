package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.feign.RecipeServiceClient;
import com.plan_menu.meal_plan_service.service.impl.RecipeServiceImpl;
import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceUnitTest {
    @Mock
    RecipeServiceClient client;
    @InjectMocks
    RecipeServiceImpl recipeService;

    @DisplayName("Выбрасывает исключение если запрос не правильный ")
    @Test
    public void shouldReturnExceptionWhenSomethingWentWrong() {
        //
        doThrow(FeignException.class).when(client).getListRecipes(any());
        //
        assertThrows(FeignException.class, () -> {
            recipeService.getRecipes(List.of(1L, 3L, 4L, 5L));
        });
    }
}
