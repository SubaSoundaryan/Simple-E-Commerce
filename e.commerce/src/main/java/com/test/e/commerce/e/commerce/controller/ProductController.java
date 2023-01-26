package com.test.e.commerce.e.commerce.controller;

import com.test.e.commerce.e.commerce.dto.ProductDto;
import com.test.e.commerce.e.commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.add(productDto), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.update(productDto), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<ProductDto> getOne(@RequestParam("id") Long id){
        return new ResponseEntity<>(productService.getOne(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getAll(){
        return new ResponseEntity<>(productService.getList(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") Long id){
        return new ResponseEntity<>(productService.delete(id), HttpStatus.ACCEPTED);
    }
}
