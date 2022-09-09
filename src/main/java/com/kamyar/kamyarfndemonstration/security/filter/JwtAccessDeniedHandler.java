package com.kamyar.kamyarfndemonstration.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static com.kamyar.kamyarfndemonstration.enums.ResultMessage.ACCESS_DENIED_MESSAGE;


@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        OutputStream outputStream = response.getOutputStream();
        new ObjectMapper().writeValue(outputStream, HttpResponse.create(ACCESS_DENIED_MESSAGE.getCode(), ACCESS_DENIED_MESSAGE.getMessage()));
        outputStream.flush();
    }
}
