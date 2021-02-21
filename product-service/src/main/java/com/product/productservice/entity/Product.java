package com.product.productservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="prodSeqGen")
    @SequenceGenerator(name = "prodSeqGen", sequenceName = "prod_id_seq", allocationSize=1)
    private Long id;
    private String name;
    private String description;
    private double unitPrice;
    private int units;
    private Date createdDate;
    private Date updatedDate;


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", units=" + units +
                '}';
    }
}
