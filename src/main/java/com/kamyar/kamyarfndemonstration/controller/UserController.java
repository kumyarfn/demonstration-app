package com.kamyar.kamyarfndemonstration.controller;

import com.kamyar.kamyarfndemonstration.dto.request.user.UserRegistrationDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kamyar.kamyarfndemonstration.enums.Role.USER;

@RestController @RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> registerUser(@RequestBody @Valid UserRegistrationDto dto) {
        return ResponseEntity.ok(userService.registerUser(dto, USER));
    }

}
