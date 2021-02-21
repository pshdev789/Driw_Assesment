package com.product.productservice.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductRequest {


    private String name;
    private String description;
    private double unitPrice;
    private int units;

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", units=" + units +
                '}';
    }
}
