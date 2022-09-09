package com.kamyar.kamyarfndemonstration.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

import static com.kamyar.kamyarfndemonstration.enums.Constants.*;

@Getter @AllArgsConstructor
public enum Role {

    USER (USER_AUTHORITIES),
    PRODUCT_PROVIDER(PRODUCT_PROVIDER_AUTHORITIES),
    PRODUCT_MANAGER(PRODUCT_MANAGER_AUTHORITIES),
    ADMIN (ADMIN_AUTHORITIES),
    SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES);

    private final List<String> Authorities;
}
