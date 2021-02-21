package com.product.productservice.services;

import com.product.productservice.entity.Product;
import com.product.productservice.exceptions.CommonException;
import com.product.productservice.exceptions.InvalidPriceException;
import com.product.productservice.exceptions.ProductNotFoundException;
import com.product.productservice.repositories.ProductRepository;
import com.product.productservice.requests.ProductRequest;
import com.product.productservice.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Service
public class ProductService {

    private Logger logger;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.productRepository = productRepository;
    }

    public void saveProduct(Product productRequest) throws InvalidPriceException{
        logger.info(Util.TWO_BRACKETS_VALUE, Util.SAVE_PRODUCT_SERVICE,productRequest);
        try {
            if (productRequest.getUnitPrice() == 0.00) {
                throw new InvalidPriceException();
            }
            Product product = productRepository.findByName(productRequest.getName());
            if (product == null) {
                logger.info(Util.THREE_BRACKETS_VALUE, Util.SAVE_PRODUCT, productRequest, "ADDING_PRODUCT");
                product = new Product();
                product.setCreatedDate(new Date(System.currentTimeMillis()));

            } else {
                logger.info(Util.TWO_BRACKETS_VALUE, Util.SAVE_PRODUCT, "UPDATING_PRODUCT");
                product.setUpdatedDate(new Date(System.currentTimeMillis()));
            }

            productRepository.save(product);
        }catch (Exception ex){
            logger.error(Util.THREE_BRACKETS_VALUE, Util.SAVE_PRODUCT, "UPDATING_PRODUCT_FAILED");
        }
    }

    public Product getProduct(long id)throws  ProductNotFoundException,CommonException{

        logger.info(Util.TWO_BRACKETS_VALUE, Util.SAVE_PRODUCT_SERVICE);
        Product product=null;
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);

            if (!optionalProduct.isPresent()) {

                throw new ProductNotFoundException();
            }
            product=optionalProduct.get();
        }catch (ProductNotFoundException ex){
            logger.error(Util.TWO_BRACKETS_VALUE, Util.VIEW_PRODUCT_SERVICE, ex.getMessage());
            throw new ProductNotFoundException();

        }catch (Exception ex){
            logger.error(Util.TWO_BRACKETS_VALUE, Util.VIEW_PRODUCT_SERVICE, "VIEWING_PRODUCT_FAILED");
            throw new CommonException();

        }
        return product;
    }

    public List<Product> getAllProducts() throws  ProductNotFoundException,CommonException{

        logger.info(Util.TWO_BRACKETS_VALUE, Util.VIEW_PRODUCT_SERVICE);
        List<Product> prodList;
              try {

                prodList= (List<Product>) productRepository.findAll();

        if (prodList.isEmpty()){
            throw  new ProductNotFoundException();
        }


    }catch (ProductNotFoundException ex){
        logger.error(Util.TWO_BRACKETS_VALUE, Util.VIEW_PRODUCT_SERVICE, ex.getMessage());
        throw new ProductNotFoundException();

    }catch (Exception ex){
        logger.error(Util.TWO_BRACKETS_VALUE, Util.VIEW_PRODUCT_SERVICE, "VIEWING_PRODUCT_FAILED");
        throw new CommonException();

    }
        return prodList;
    }


}
