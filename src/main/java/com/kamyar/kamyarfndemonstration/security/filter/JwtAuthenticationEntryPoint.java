package com.kamyar.kamyarfndemonstration.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static com.kamyar.kamyarfndemonstration.enums.ResultMessage.ACCESS_DENIED_MESSAGE;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        new ObjectMapper().writeValue(outputStream, HttpResponse.create(ACCESS_DENIED_MESSAGE.getCode(), ACCESS_DENIED_MESSAGE.getMessage()));
        outputStream.flush();
    }
}
