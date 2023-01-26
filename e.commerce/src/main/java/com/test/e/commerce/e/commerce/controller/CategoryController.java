package com.test.e.commerce.e.commerce.controller;


import com.test.e.commerce.e.commerce.dto.CategoryDto;
import com.test.e.commerce.e.commerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> add(@RequestParam("name") String name){
        return new ResponseEntity<>(categoryService.add(name), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestParam("id") Long id, @RequestParam("name") String name){
        return new ResponseEntity<>(categoryService.update(id, name), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<String> update(@RequestParam("id") Long id){
        return new ResponseEntity<>(categoryService.delete(id), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<CategoryDto> getOne(@RequestParam("id") Long id){
        return new ResponseEntity<>(categoryService.getOne(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> getList() throws TimeoutException {
        return new ResponseEntity<>(categoryService.getList(), HttpStatus.ACCEPTED);
    }
}
