package com.example.demo.model.message.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCategoryReq {
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name must be not Empty")
    private String name;
}
