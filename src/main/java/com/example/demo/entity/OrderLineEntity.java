package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderLineEntity {
    @EmbeddedId
    OrderLineKey id;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    OrderEntity orderEntity;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;


    @NotNull
    @Column(name = "amount")
    private int amount;

    @Column(name = "purchasePrice")
    private BigDecimal purchasePrice;

}
