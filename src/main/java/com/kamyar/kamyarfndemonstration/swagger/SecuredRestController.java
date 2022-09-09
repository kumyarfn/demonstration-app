package com.kamyar.kamyarfndemonstration.swagger;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import static com.kamyar.kamyarfndemonstration.enums.Constants.BEARER_AUTH;

@SecurityRequirement(name = BEARER_AUTH)
public interface SecuredRestController {
}
