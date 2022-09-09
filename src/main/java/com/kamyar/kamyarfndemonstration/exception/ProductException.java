package com.kamyar.kamyarfndemonstration.exception;

import com.kamyar.kamyarfndemonstration.enums.ResultMessage;

public class ProductException extends BaseException{
    public ProductException(String message, Integer code) {
        super(message, code);
    }

    public ProductException(ResultMessage exceptionConstant) {
        super(exceptionConstant);
    }
}
