package com.kamyar.kamyarfndemonstration.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum Security {

        AUTHORITIES("Authorities"),
        JWT_TOKEN_HEADER("Jwt-Token"),
        KAMYAR_LLC("KAMYAR CO. COPYRIGHT Ⓒ2022"),
        AUDIENCE("ADMIN||USER"),
        EXPIRATION_TIME("55000000"),
        TOKEN_PREFIX("Bearer "),
        OPTIONS_HTTP_METHOD("OPTIONS");

    private final String value;


}
