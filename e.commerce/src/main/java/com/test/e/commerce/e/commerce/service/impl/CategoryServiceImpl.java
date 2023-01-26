package com.test.e.commerce.e.commerce.service.impl;

import com.test.e.commerce.e.commerce.dto.CategoryDto;
import com.test.e.commerce.e.commerce.entity.CategoryEntity;
import com.test.e.commerce.e.commerce.exception.ResourceNotFound;
import com.test.e.commerce.e.commerce.repository.CategoryRepository;
import com.test.e.commerce.e.commerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public String add(String name){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setSkuType(categoryRepository.count() + 1 + "000xxxx");
        categoryRepository.save(categoryEntity);
        return "Category Added Successfully";
    }

    @Override
    public String update(Long id, String name){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if(categoryEntity == null)
            throw new ResourceNotFound("Category Not Found");

        categoryEntity.setName(name);
        categoryRepository.save(categoryEntity);
        return "Category Updated Successfully";
    }

    @Override
    public String delete(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if(categoryEntity == null)
            throw new ResourceNotFound("Category Not Found");

        categoryRepository.delete(categoryEntity);
        return "Category Deleted Successfully";
    }

    @Override
    public CategoryDto getOne(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if(categoryEntity == null)
            throw new ResourceNotFound("Category Not Found");

        return new CategoryDto(categoryEntity.getId(), categoryEntity.getName());
    }

    @Override
    public List<CategoryDto> getList() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categoryDtoList.add(new CategoryDto(categoryEntity.getId(), categoryEntity.getName()));
        }

        return categoryDtoList;
    }
}
