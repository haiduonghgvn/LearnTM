package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderLineKey implements Serializable {
    @Column(name = "order_id")
    long orderID;
    @Column(name = "product_id")
    long productID;
}
