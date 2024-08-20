package com.plan_menu.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Общее исключение для сервиса списков покупок.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ShoppingListServiceException extends RuntimeException {
    public ShoppingListServiceException(String message) {
        super(message);
    }

    public ShoppingListServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
