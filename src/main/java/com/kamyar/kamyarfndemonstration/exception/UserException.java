package com.kamyar.kamyarfndemonstration.exception;

import com.kamyar.kamyarfndemonstration.enums.ResultMessage;

public class UserException extends BaseException{
    public UserException(String message, Integer code) {
        super(message, code);
    }

    public UserException(ResultMessage exceptionConstant) {
        super(exceptionConstant);
    }
}
