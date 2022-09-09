package com.kamyar.kamyarfndemonstration.controller;

import com.kamyar.kamyarfndemonstration.dto.request.product.ProductAddingDto;
import com.kamyar.kamyarfndemonstration.dto.request.product.ProductUpdateDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController @RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @PreAuthorize("hasAuthority('pp::write')")
    public ResponseEntity<HttpResponse> addProduct(@RequestBody @Valid ProductAddingDto dto){
        return ResponseEntity.ok(productService.addProduct(dto));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('pp::write')")
    public ResponseEntity<HttpResponse> updateProduct(@RequestBody @Valid ProductUpdateDto dto){
        return ResponseEntity.ok(productService.updateProduct(dto));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('user::read')")
    public ResponseEntity<HttpResponse> searchProducts
            (@RequestParam String providerId, @RequestParam Double minPrice, @RequestParam Double maxPrice,
             @RequestParam Boolean isAvailable, @RequestParam String sort, @RequestParam Sort.Direction sortDirection,
             @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return ResponseEntity.ok(productService.searchProducts(providerId, isAvailable, minPrice, maxPrice, sort, sortDirection, pageNumber, pageSize));
    }


}
