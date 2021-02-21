package com.product.productservice.exceptions;

import com.product.productservice.utils.Util;

public class CommonException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "COMMON_FAILIURE";
    }
}