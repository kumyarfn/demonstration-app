package com.kamyar.kamyarfndemonstration.exception;

import com.kamyar.kamyarfndemonstration.enums.ResultMessage;

public class ProviderException extends BaseException{
    public ProviderException(String message, Integer code) {
        super(message, code);
    }

    public ProviderException(ResultMessage exceptionConstant) {
        super(exceptionConstant);
    }
}
