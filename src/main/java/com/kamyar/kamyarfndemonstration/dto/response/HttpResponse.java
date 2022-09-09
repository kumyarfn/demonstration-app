package com.kamyar.kamyarfndemonstration.dto.response;

import com.kamyar.kamyarfndemonstration.enums.ResultMessage;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HttpResponse {

    private Integer code;

    private Object data;

    private HttpResponse(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static HttpResponse create(Integer code, Object data){
        return new HttpResponse(code, data);
    }

    public static HttpResponse create(ResultMessage resultMessage){
        return new HttpResponse(resultMessage.getCode(), resultMessage.getMessage());
    }
}
