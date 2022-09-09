package com.kamyar.kamyarfndemonstration.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {

    public static final String[] PUBLIC_URLS = {"/login", "/user/register",
            "/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html", "/v3/api-docs/**"};

    public static final List<String> USER_AUTHORITIES = List.of("user::read");
    public static final List<String> PRODUCT_PROVIDER_AUTHORITIES = Stream.concat(USER_AUTHORITIES.stream(),
            Stream.of("pp::read", "pp::write")).collect(Collectors.toList());
    public static final List<String> PRODUCT_MANAGER_AUTHORITIES = Stream.concat(PRODUCT_PROVIDER_AUTHORITIES.stream(),
            Stream.of("pm::read", "pm::write")).collect(Collectors.toList());
    public static final List<String> ADMIN_AUTHORITIES = Stream.concat(PRODUCT_MANAGER_AUTHORITIES.stream(),
            Stream.of("admin::read", "admin::write")).collect(Collectors.toList());
    public static final List<String> SUPER_ADMIN_AUTHORITIES = Stream.concat(ADMIN_AUTHORITIES.stream(),
            Stream.of("SUPER::ADMIN")).collect(Collectors.toList());

    public static final String BEARER_AUTH = "bearerAuth";


    public static final String COMMENT_COLLECTION_NAME = "comment";
    public static final String PRODUCT_COLLECTION_NAME = "product";
    public static final String PROVIDER_COLLECTION_NAME = "provider";
    public static final String SALE_COLLECTION_NAME = "sale";
    public static final String USER_COLLECTION_NAME = "user";
    public static final String VOTE_COLLECTION_NAME = "vote";

    public static final String PRODUCTID_IN_PRODUCT_Field = "products.productId";
    public static final String STATUS_Field = "status";
    public static final String USER_ID_Field = "userId";
    public static final String PRODUCT_ID_FIELD = "productId";
    public static final String PROVIDER_ID_FIELD = "providerId";
    public static final String IS_APPROVED_FIELD = "isApproved";
    public static final String IS_AVAILABLE_FIELD = "isAvailable";
    public static final String CREATION_DATE_FIELD = "creationDate";
    public static final String PRICE_FIELD = "price";


}
