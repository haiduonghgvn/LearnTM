package com.example.demo.model.mapper;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.message.request.UpdateUserReq;

import java.util.Date;

public class ProductMapper {

    public static ProductDto toProductDto(Product product) {
        ProductDto temp = new ProductDto();
        temp.setId(product.getId());
        temp.setComment(product.getComment());
        temp.setPrice(product.getPrice());
        temp.setName(product.getName());
        temp.setDescription(product.getDescription());
        temp.getInStock(product.getInStock());
        temp.getImage(product.getImage());
        return temp;
    }
}
