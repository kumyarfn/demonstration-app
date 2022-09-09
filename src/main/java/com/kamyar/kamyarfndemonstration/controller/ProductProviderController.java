package com.kamyar.kamyarfndemonstration.controller;

import com.kamyar.kamyarfndemonstration.dto.request.provider.ProviderRegistrationDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController @RequestMapping("/provider")
public class ProductProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('admin::write')")
    public ResponseEntity<HttpResponse> registerProvider(@RequestBody @Valid ProviderRegistrationDto dto) {
        return ResponseEntity.ok(providerService.registerProvider(dto));
    }

}
