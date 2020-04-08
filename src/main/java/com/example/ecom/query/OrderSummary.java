package com.example.ecom.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.spi.DirObjectFactory;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class OrderSummary {
    @Id
    private String id;
    private Double price;
    private Integer number;

    @ManyToOne
    @JoinColumn(name="productid", insertable = false, updatable = false)
    private ProductSummary summary;

    private String  productid;

    public OrderSummary(String id, Double price, Integer number, String productid) {
        this.id = id;
        this.price = price;
        this.number = number;
        this.productid = productid;
    }
}
