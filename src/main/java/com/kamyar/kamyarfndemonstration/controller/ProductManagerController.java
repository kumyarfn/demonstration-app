package com.kamyar.kamyarfndemonstration.controller;

import com.kamyar.kamyarfndemonstration.dto.request.user.UserRegistrationDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.kamyar.kamyarfndemonstration.enums.Role.PRODUCT_MANAGER;

@RestController @RequestMapping("/product-manager")
public class ProductManagerController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('admin::write')")
    public ResponseEntity<HttpResponse> registerProductManager(@RequestBody @Valid UserRegistrationDto dto){
        return ResponseEntity.ok(userService.registerUser(dto, PRODUCT_MANAGER));
    }

}
