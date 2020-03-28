package com.example.demo.model.mapper;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.dto.ProductDto;

import java.util.List;

public class CategoryMapper {

    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto temp = new CategoryDto();
        temp.setId(category.getId());
        temp.setName(category.getName());
        temp.setProduct(category.getProduct());
        return temp;
    }
}
