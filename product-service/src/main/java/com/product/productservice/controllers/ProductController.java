package com.product.productservice.controllers;

import com.product.productservice.entity.Product;
import com.product.productservice.exceptions.InvalidPriceException;
import com.product.productservice.exceptions.ProductNotFoundException;
import com.product.productservice.requests.ProductRequest;
import com.product.productservice.services.ProductService;
import com.product.productservice.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private Logger logger;
    private ProductService productService;
    private Util utils;

    @Autowired
    public ProductController(ProductService productService) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.productService=productService;

    }

    @PostMapping("/add")
    public Map<String, Object> addProduct(@RequestBody Product product) {
        logger.info(Util.TWO_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER,product );
        Map<String, Object> response = new HashMap<>();
        try {

            productService.saveProduct(product);
            response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
            response.put(Util.MESSAGE_VALUE, Util.SAVE_PRODUCT_SUCCESS_MESSAGE);
            logger.info(Util.TWO_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER, Util.STATUS_SUCCESS);
        }
        catch (InvalidPriceException ex) {
            logger.error(Util.THREE_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER, ex.getMessage(),
                    Util.STATUS_FAILED);
            response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
            response.put(Util.MESSAGE_VALUE, Util.INVALID_UNIT_PRICE_EXCEPTION);
            logger.info(Util.THREE_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER,  response);
            return response;
        }catch (Exception ex) {
            logger.error(Util.THREE_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER, ex.getMessage(),
                    Util.STATUS_FAILED);
            response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
            response.put(Util.MESSAGE_VALUE, Util.SAVE_PRODUCT_FAILED_MESSAGE);
            logger.info(Util.THREE_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER,  response);
            return response;
        }
        logger.info(Util.THREE_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER,  response);
        return response;
    }


    @GetMapping("/product/{id}")
    public Map<String, Object> getProduct(@PathVariable("id") long id) {
        logger.info(Util.VIEW_PRODUCT_CONTROLLER);
        Map<String, Object> response = new HashMap<>();
        Product product;
        try {
            product = productService.getProduct(id);
            response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
            response.put(Util.MESSAGE_VALUE, Util.VIEW_PRODUCT_SUCCESS_MESSAGE);
            response.put(Util.DATA_VALUE, product);
            logger.info(Util.TWO_BRACKETS_VALUE, Util.VIEW_PRODUCT_CONTROLLER, Util.STATUS_SUCCESS);
        }  catch (ProductNotFoundException ex) {
            logger.error(Util.THREE_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER, ex.getMessage(),
                    Util.STATUS_FAILED);
            response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
            response.put(Util.MESSAGE_VALUE, Util.PRODUCT_NOT_FOUND_MESSAGE);
            logger.info(Util.THREE_BRACKETS_VALUE, Util.VIEW_PRODUCT_CONTROLLER,  response);
            return response;
        }
        catch (Exception ex) {
            logger.error(Util.THREE_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER, ex.getMessage(),
                    Util.STATUS_FAILED);
            response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
            response.put(Util.MESSAGE_VALUE, Util.VIEW_PRODUCT_FAILED_MESSAGE);
            logger.info(Util.THREE_BRACKETS_VALUE, Util.VIEW_PRODUCT_CONTROLLER,  response);
            return response;
        }
        return response;
    }


    @GetMapping("/allproducts")
    public Map<String, Object>  getAllProducts(){
        logger.info(Util.VIEW_PRODUCT_CONTROLLER);
        Map<String, Object> response = new HashMap<>();
        List<Product> products;
        try {
            products = productService.getAllProducts();
            response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
            response.put(Util.MESSAGE_VALUE, Util.VIEW_PRODUCT_SUCCESS_MESSAGE);
            response.put(Util.DATA_VALUE, products);
            logger.info(Util.TWO_BRACKETS_VALUE, Util.VIEW_PRODUCT_CONTROLLER, Util.STATUS_SUCCESS);
        }  catch (Exception ex) {
            logger.error(Util.THREE_BRACKETS_VALUE, Util.ADD_PRODUCT_CONTROLLER, ex.getMessage(),
                    Util.STATUS_FAILED);
            response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
            response.put(Util.MESSAGE_VALUE, Util.VIEW_PRODUCT_FAILED_MESSAGE);
            logger.info(Util.THREE_BRACKETS_VALUE, Util.VIEW_PRODUCT_CONTROLLER,  response);
            return response;
        }
        return response;

    }

}
