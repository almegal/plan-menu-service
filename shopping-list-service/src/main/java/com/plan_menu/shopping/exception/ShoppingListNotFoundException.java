package com.plan_menu.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое при отсутствии списка покупок в базе данных.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShoppingListNotFoundException extends RuntimeException {
    public ShoppingListNotFoundException(String message) {
        super(message);
    }
}
