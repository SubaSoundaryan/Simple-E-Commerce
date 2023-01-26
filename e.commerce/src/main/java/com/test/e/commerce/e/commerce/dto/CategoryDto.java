package com.test.e.commerce.e.commerce.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CategoryDto {
    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;
}
