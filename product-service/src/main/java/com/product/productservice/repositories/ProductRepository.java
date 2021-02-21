package com.product.productservice.repositories;

import com.product.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository {

    public Product findByName(String name);

}
