package com.test.e.commerce.e.commerce.service;

import com.test.e.commerce.e.commerce.dto.ProductDto;

import java.util.List;

public interface ProductService {

    String add(ProductDto productDto);
    String update(ProductDto productDto);
    String delete(Long id);
    ProductDto getOne(Long id);
    List<ProductDto> getList();
}
