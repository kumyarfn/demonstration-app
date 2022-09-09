package com.kamyar.kamyarfndemonstration.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum ResultMessage {

    PRODUCT_WAS_SUCCESSFULLY_UPDATED(18, "Product was successfully updated."),
    PROVIDER_WAS_SUCCESSFULLY_UPDATED(17, "Product was successfully updated."),
    PRODUCT_WAS_SUCCESSFULLY_ADDED(14, "Product was successfully saved"),
    COMMENT_SUCCESSFULLY_APPROVED(13, "Comment was successfully approved."),
    COMMENT_SUCCESSFULLY_SAVED(12, "Comment was successfully saved."),
    VOTE_SUCCESSFULLY_APPROVED(11, "Vote was successfully approved."),
    VOTE_SUCCESSFULLY_SAVED(10, "Vote was successfully saved."),
    USER_LOGIN_WAS_SUCCESSFUL(3, "Login was successful."),
    USER_REGISTERED_SUCCESSFULLY(2, "User was successfully registered."),
    SUCCESS_RESULT(1, ""),

    VALIDATION(-1, "Required request body is wrong."),
    ACCESS_DENIED_MESSAGE(-2,"You don't have permission to access this page."),
    TOKEN_CAN_NOT_BE_VERIFIED( -3,"JWT was not verified."),
    PAGE_NOT_FOUND(-4, "Page was not found."),
    METHOD_NOT_ALLOWED(-5, "Method for the http request is wrong."),
    USER_BANNED(-6, "User is banned from using this website;"),
    USERNAME_ALREADY_EXISTS(-7, "Username already exists."),
    PHONE_NUMBER_ALREADY_EXISTS(-8, "Phone number already exists."),
    PROVIDER_USERNAME_ALREADY_EXISTS(-9, "Provider username already exists."),
    PROVIDER_OFFICIAL_NAME_ALREADY_EXISTS(-10, "Provider official name already exists."),
    AUTHENTICATION_EXCEPTION(-11, "Username or Password is wrong."),
    PRODUCT_ID_IS_WRONG(-12, "Product with the id specified was not found."),
    VOTE_ID_IS_WRONG(-13, "Vote with the id specified was not found."),
    COMMENT_ID_IS_WRONG(-14, "Comment with the id specified was not found."),
    PRODUCT_VOTING_IS_NOT_ENABLEd(-15, "Product voting is not enabled at this moment."),
    USER_HAS_NOT_BOUGHT_PRODUCT(-16, "User has not bought the specified product"),
    PROVIDER_WAS_NOT_FOUND(-17, "Provider with the id specified was not found."),
    PRODUCT_COMMENT_IS_NOT__ENABLED(-18, "Product commenting is not enabled at this moment."),
    PRODUCT_SEARCH_SORT_EXCEPTION(-19, "sort is empty."),
    PRODUCT_SEARCH_SORT_DIRECTION_EXCEPTION(-20, "sortDirection is empty."),
    PRODUCT_SEARCH_PAGE_NUMBER_EXCEPTION(-21, "pageNumber is empty."),
    PRODUCT_SEARCH_PAGE_SIZE_EXCEPTION(-22, "pageSize is empty."),
    USER_WAS_NOT_FOUND(-23, "User was not found."),
    USER_HAS_VOTED_BEFORE(-24, "User has already voted on this product."),
    USER_HAS_COMMENTED_BEFORE(-25, "User has already commented on this product."),
    EXCEPTION(-30, "");


    private final Integer code;

    private final String message;
}
