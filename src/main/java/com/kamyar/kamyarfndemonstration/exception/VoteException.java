package com.kamyar.kamyarfndemonstration.exception;

import com.kamyar.kamyarfndemonstration.enums.ResultMessage;

public class VoteException extends BaseException{
    public VoteException(String message, Integer code) {
        super(message, code);
    }

    public VoteException(ResultMessage exceptionConstant) {
        super(exceptionConstant);
    }
}
