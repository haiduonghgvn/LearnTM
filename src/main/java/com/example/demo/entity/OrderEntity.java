package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(name = "Last_Update")
    private Date lastupdate;

    @Column(name = "amount")
    private int amount;

    @Column(name = "totalPrice")
    private float   totalPrice;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
