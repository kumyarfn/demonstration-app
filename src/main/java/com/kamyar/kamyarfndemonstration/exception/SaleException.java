package com.kamyar.kamyarfndemonstration.exception;

import com.kamyar.kamyarfndemonstration.enums.ResultMessage;

public class SaleException extends BaseException{
    public SaleException(String message, Integer code) {
        super(message, code);
    }

    public SaleException(ResultMessage exceptionConstant) {
        super(exceptionConstant);
    }
}
