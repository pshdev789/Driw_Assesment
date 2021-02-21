package com.product.productservice.exceptions;

import com.product.productservice.utils.Util;

public class InvalidPriceException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return Util.INVALID_UNIT_PRICE_EXCEPTION;
    }
}