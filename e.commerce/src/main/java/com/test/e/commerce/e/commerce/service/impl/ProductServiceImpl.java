package com.test.e.commerce.e.commerce.service.impl;

import com.test.e.commerce.e.commerce.dto.ProductDto;
import com.test.e.commerce.e.commerce.entity.CategoryEntity;
import com.test.e.commerce.e.commerce.entity.ProductEntity;
import com.test.e.commerce.e.commerce.exception.ResourceNotFound;
import com.test.e.commerce.e.commerce.repository.CategoryRepository;
import com.test.e.commerce.e.commerce.repository.ProductRepository;
import com.test.e.commerce.e.commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public String add(ProductDto productDto){
        CategoryEntity categoryEntity = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(categoryEntity == null)
            throw new ResourceNotFound("Category Not Found");

        Long skuId = Long.valueOf(categoryEntity.getSkuType().substring(0,categoryEntity.getSkuType().length() - 7));
        Long productCount = productRepository.countAvailableProductByCategoryId(categoryEntity.getId());

        String sku = skuId + "000000" + (productCount + 1);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDto.getName());
        productEntity.setCategoryId(productDto.getCategoryId());
        productEntity.setStock(productDto.getStock());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setCurrency(productDto.getCurrency());
        productEntity.setSku(sku);
        productRepository.save(productEntity);
        return "Product Added SuccessFully";
    }

    @Override
    public String update(ProductDto productDto){
        ProductEntity productEntity = productRepository.findById(productDto.getId()).orElse(null);
        if(productEntity == null)
            throw new ResourceNotFound("product not found");

        productEntity.setName(productDto.getName());
        productEntity.setCategoryId(productDto.getCategoryId());
        productEntity.setPrice(productEntity.getPrice());
        productEntity.setStock(productEntity.getStock());
        productEntity.setCurrency(productEntity.getCurrency());
        productRepository.save(productEntity);
        return "Product Updated SuccessFully";
    }

    @Override
    public String delete(Long id){
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if(productEntity == null)
            throw new ResourceNotFound("product not found");

        productRepository.delete(productEntity);
        return "Product Deleted SuccessFully";
    }

    @Override
    public ProductDto getOne(Long id){
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if(productEntity == null)
            throw new ResourceNotFound("product not found");

        return getProductDto(productEntity);
    }

    @Override
    public List<ProductDto> getList(){
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductEntity product : productEntities) {
            productDtoList.add(getProductDto(product));
        }
        return productDtoList;
    }

    public ProductDto getProductDto(ProductEntity productEntity){
        ProductDto productDto = new ProductDto();
        productDto.setName(productEntity.getName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setStock(productEntity.getStock());
        productDto.setCurrency(productEntity.getCurrency());
        productDto.setId(productEntity.getId());
        productDto.setCategoryName(productEntity.getCategoryEntity().getName());
        return productDto;
    }
}
