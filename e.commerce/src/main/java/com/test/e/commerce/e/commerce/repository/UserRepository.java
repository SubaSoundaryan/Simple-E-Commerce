package com.test.e.commerce.e.commerce.repository;

import com.test.e.commerce.e.commerce.entity.CategoryEntity;
import com.test.e.commerce.e.commerce.entity.UserEntity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByApiKey(String apiKey);
    UserEntity findByName(String name);

    @Query(value = "select IF(((now() - interval 1 hour) < :date) = 1, 'true', 'false')", nativeQuery = true)
    Boolean isKeyHaveValidTime(@Param("date") Date date);
}
