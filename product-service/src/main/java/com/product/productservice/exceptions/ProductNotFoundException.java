package com.product.productservice.exceptions;

import com.product.productservice.utils.Util;

public class ProductNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return Util.PRODUCT_NOT_FOUND_MESSAGE;
    }
}