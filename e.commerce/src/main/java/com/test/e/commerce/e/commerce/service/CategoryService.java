package com.test.e.commerce.e.commerce.service;

import com.test.e.commerce.e.commerce.dto.CategoryDto;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface CategoryService {
    String add(String name);
    String update(Long id, String name);
    String delete(Long id);
    CategoryDto getOne(Long id);
    List<CategoryDto> getList() throws TimeoutException;
}
