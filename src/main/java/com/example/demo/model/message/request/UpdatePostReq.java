package com.example.demo.model.message.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdatePostReq {
    @NotEmpty(message = "CategoryID must be not Empty")
    private int categoryID;

    @NotNull(message = "Image is required")
    @NotEmpty(message = "Image must be not Empty")
    private String img;

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title must be not Empty")
    private String title;

    @NotNull(message = "Content is required")
    @NotEmpty(message = "Content must be not Empty")
    private String content;
}
