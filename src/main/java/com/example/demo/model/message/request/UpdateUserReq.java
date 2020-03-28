package com.example.demo.model.message.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateUserReq {
    @ApiModelProperty(
            example="Sam Smith",
            notes="Name cannot be empty",
            required=true
    )
    @NotNull
    private String name;

    @NotNull
    private String avatar;

    @NotNull
    private String role;
}
