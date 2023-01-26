package com.test.e.commerce.e.commerce.repository;

import com.test.e.commerce.e.commerce.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "select count(*) from product where category_id=:categoryId", nativeQuery = true)
    Long countAvailableProductByCategoryId(@Param("categoryId") Long categoryId);
}
