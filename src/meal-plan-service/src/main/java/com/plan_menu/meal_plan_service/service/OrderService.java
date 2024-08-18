package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.ShoppingListRequestDto;
import feign.FeignException;
import org.springframework.http.HttpStatus;

/**
 * Интерфейс для сервиса заказов, предоставляющий метод для оформления заказа на продукты.
 */
public interface OrderService {

    /**
     * Оформляет заказ на продукты, основываясь на переданном списке покупок.
     *
     * @param shopList объект ShoppingListRequestDto, содержащий список продуктов для заказа.
     * @return HttpStatus, представляющий статус ответа от внешнего сервиса заказа.
     * @throws FeignException если возникает ошибка при взаимодействии с внешним сервисом через Feign клиент.
     */
    HttpStatus makeOrder(ShoppingListRequestDto shopList) throws FeignException;
}
