package com.example.demo.model.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;

    private String userName;

    private String password;

    private String email;

    private String address;

    private String role;

    private Date createDate;

    private Date updateDate;

    private String avatar;
}
