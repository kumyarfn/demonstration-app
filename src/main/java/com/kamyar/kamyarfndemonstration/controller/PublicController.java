package com.kamyar.kamyarfndemonstration.controller;

import com.kamyar.kamyarfndemonstration.dto.request.LoginDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> userLogin(@RequestBody @Valid LoginDto dto) {
        return userService.authenticate(dto.getUsername(),dto.getPassword());
    }

}
