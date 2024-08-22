package com.plan_menu.shopping.repository;

import com.plan_menu.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с продуктами.
 * Предоставляет методы для выполнения CRUD операций и кастомных запросов к таблице продуктов.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Находит продукт по его названию.
     *
     * @param productTitle название продукта.
     * @return Optional, содержащий найденный продукт, если таковой существует.
     */
    Optional<Product> findByTitle(String productTitle);

    /**
     * Возвращает список продуктов по их идентификаторам.
     *
     * @param productIds список идентификаторов продуктов.
     * @return список продуктов с указанными идентификаторами.
     */
    @Query("SELECT p FROM Product p WHERE p.id IN :productIds")
    List<Product> findByIds(List<Long> productIds);

    /**
     * Проверяет, доступен ли продукт на складе в необходимом количестве.
     *
     * @param productId идентификатор продукта.
     * @param count необходимое количество продукта.
     * @return true, если продукт доступен в указанном количестве, иначе false.
     */
    @Query("SELECT (count(p) > 0) FROM Product p WHERE p.id = :productId AND p.countOnStorage >= :count")
    boolean isProductAvailable(Long productId, int count);

    /**
     * Проверяет доступность продуктов в списке.
     *
     * @param productIds список идентификаторов продуктов.
     * @param counts список необходимых количеств продуктов.
     * @return список доступных продуктов.
     */
    @Query("SELECT p FROM Product p WHERE p.id IN :productIds AND p.countOnStorage >= (SELECT count FROM :counts WHERE productId = p.id)")
    List<Product> areProductsAvailable(List<Long> productIds, List<Integer> counts);

    /**
     * Находит продукты по части названия.
     *
     * @param titlePart часть названия продукта.
     * @return список продуктов, содержащих указанную часть названия.
     */
    @Query("SELECT p FROM Product p WHERE p.title LIKE %:titlePart%")
    List<Product> findByTitleContaining(String titlePart);

    /**
     * Находит доступные продукты по идентификатору продукта.
     *
     * @param productId идентификатор продукта.
     * @return список доступных продуктов.
     */
    @Query("SELECT p FROM Product p WHERE p.id = :productId AND p.countOnStorage > 0")
    List<Product> findAvailableProducts(Long productId);

    /**
     * Находит доступные продукты по частичному названию.
     *
     * @param titlePart часть названия продукта.
     * @return список доступных продуктов, содержащих указанную часть названия.
     */
    @Query("SELECT p FROM Product p WHERE p.title LIKE %:titlePart% AND p.countOnStorage > 0")
    List<Product> findAvailableProductsByTitleContaining(String titlePart);

    /**
     * Находит продукты по их статусу.
     *
     * @param status статус продукта.
     * @return список продуктов с указанным статусом.
     */
    @Query("SELECT p FROM Product p WHERE p.status = :status")
    List<Product> findByStatus(String status);
}
