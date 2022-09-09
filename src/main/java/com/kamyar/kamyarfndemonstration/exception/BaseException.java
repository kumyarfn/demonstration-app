package com.kamyar.kamyarfndemonstration.exception;

import com.kamyar.kamyarfndemonstration.enums.ResultMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public abstract class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BaseException(ResultMessage exceptionConstant) {
        super(exceptionConstant.getMessage());
        this.code = exceptionConstant.getCode();
    }


}
