package com.example.jpademo2.entity;

import com.example.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="products")
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    public Product(){}
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
