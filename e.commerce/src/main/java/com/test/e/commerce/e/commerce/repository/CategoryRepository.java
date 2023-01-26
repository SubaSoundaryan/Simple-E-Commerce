package com.test.e.commerce.e.commerce.repository;

import com.test.e.commerce.e.commerce.entity.CategoryEntity;
import com.test.e.commerce.e.commerce.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
