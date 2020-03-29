package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(unique = true)
    private String userName;

    @NotNull
    @Column(columnDefinition = "text")
    private String password;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "text")
    private String address;

    private Date createDate;

    private Date updateDate;

    private String phone;
    private String avatar;
    @OneToMany(mappedBy="user")
    private Set<OrderEntity> orderEntities;
    @NotNull
    @Column(name = "role", columnDefinition = "varchar(255) default 'USER'")
    private String role;

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
