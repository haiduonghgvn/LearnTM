package com.example.demo.model.dto;

import com.example.demo.entity.Product;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private int id;

    private String name;

    private List<Product> product;
}
