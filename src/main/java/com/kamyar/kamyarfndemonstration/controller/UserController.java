package com.kamyar.kamyarfndemonstration.controller;

import com.kamyar.kamyarfndemonstration.dto.request.LoginDto;
import com.kamyar.kamyarfndemonstration.dto.request.provider.ProviderRegistrationDto;
import com.kamyar.kamyarfndemonstration.dto.request.user.UserRegistrationDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.service.ProviderService;
import com.kamyar.kamyarfndemonstration.service.UserService;
import com.kamyar.kamyarfndemonstration.swagger.SecuredRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kamyar.kamyarfndemonstration.enums.Role.*;

@RestController @RequestMapping("/user")
public class UserController implements SecuredRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderService providerService;

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> userLogin(@RequestBody @Valid LoginDto dto) {
        return userService.authenticate(dto.getUsername(),dto.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> registerUser(@RequestBody @Valid UserRegistrationDto dto) {
        return ResponseEntity.ok(userService.registerUser(dto, USER));
    }

    @PostMapping("/admin/register")
    @PreAuthorize("hasAuthority('SUPER::ADMIN')")
    public ResponseEntity<HttpResponse> registerAdmin(@RequestBody @Valid UserRegistrationDto dto) {
        return ResponseEntity.ok(userService.registerUser(dto, ADMIN));
    }

    @PostMapping("/product-manager/register")
    @PreAuthorize("hasAuthority('admin::write')")
    public ResponseEntity<HttpResponse> registerProductManager(@RequestBody @Valid UserRegistrationDto dto){
        return ResponseEntity.ok(userService.registerUser(dto, PRODUCT_MANAGER));
    }


    @PostMapping("/product-provider/register")
    @PreAuthorize("hasAuthority('admin::write')")
    public ResponseEntity<HttpResponse> registerProvider(@RequestBody @Valid ProviderRegistrationDto dto) {
        return ResponseEntity.ok(providerService.registerProvider(dto));
    }

}
