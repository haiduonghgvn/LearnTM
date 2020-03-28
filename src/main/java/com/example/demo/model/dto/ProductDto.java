package com.example.demo.model.dto;

import com.example.demo.entity.Category;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;
    private String name;

    private BigDecimal price;

    private int InStock;

    private String description;

    private String comment;

    private Category category;

    private String image;


    public void getInStock(int inStock) {
    }

    public void getImage(String image) {
    }
}
