package com.example.demo.entity;

import java.math.BigDecimal;

public class Cart {
    private long id;
    private String name;
    private BigDecimal price;
    private int quamlity;

    public void ToCart(Product product){
        this.id= product.getId();
        this.name = product.getName();
        this.price= product.getPrice();
        this.quamlity= 1;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getId() == ((Cart) obj).getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuamlity() {
        return quamlity;
    }

    public void setQuamlity(int quamlity) {
        this.quamlity = quamlity;
    }
}
