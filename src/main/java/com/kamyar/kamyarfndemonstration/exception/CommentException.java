package com.kamyar.kamyarfndemonstration.exception;

import com.kamyar.kamyarfndemonstration.enums.ResultMessage;

public class CommentException extends BaseException{
    public CommentException(String message, Integer code) {
        super(message, code);
    }

    public CommentException(ResultMessage exceptionConstant) {
        super(exceptionConstant);
    }
}
