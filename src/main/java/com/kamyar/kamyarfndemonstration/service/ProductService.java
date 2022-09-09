package com.kamyar.kamyarfndemonstration.service;

import com.kamyar.kamyarfndemonstration.db.entity.ProductEntity;
import com.kamyar.kamyarfndemonstration.db.entity.ProviderEntity;
import com.kamyar.kamyarfndemonstration.db.fetch.ProductFetchDto;
import com.kamyar.kamyarfndemonstration.db.repository.ProductRepository;
import com.kamyar.kamyarfndemonstration.dto.request.product.ProductAddingDto;
import com.kamyar.kamyarfndemonstration.dto.request.product.ProductUpdateDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.exception.ProductException;
import com.kamyar.kamyarfndemonstration.util.ProductQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.kamyar.kamyarfndemonstration.enums.Constants.PRODUCT_COLLECTION_NAME;
import static com.kamyar.kamyarfndemonstration.enums.ResultMessage.*;

@Service
public class ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderService providerService;

    public HttpResponse addProduct(ProductAddingDto dto){
        ProviderEntity provider = providerService.getProviderById(dto.getProviderId());
        productRepository.save(getProductFromDto(dto));
        provider.setLastAddedProduct(LocalDate.now());
        providerService.updateProviderLastAddedDate(provider);
        return HttpResponse.create(SUCCESS_RESULT.getCode(), PRODUCT_WAS_SUCCESSFULLY_ADDED.getMessage());
    }

    public HttpResponse updateProduct(ProductUpdateDto dto){
        productRepository.save(getUpdatedProduct(getProductById(dto.getProductId()), dto));
        return HttpResponse.create(SUCCESS_RESULT.getCode(), PRODUCT_WAS_SUCCESSFULLY_UPDATED);
    }

    private ProductEntity getProductFromDto(ProductAddingDto dto) {
        return new ProductEntity(null, dto.getProviderId(), dto.getTitle(), dto.getPrice(), new Date(),
                dto.getDetails(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, null);
    }


    public ProductEntity getProductById(String id){
        return productRepository.findById(id).orElseThrow(() -> new ProductException(PRODUCT_ID_IS_WRONG));
    }

    public ProductEntity saveProduct(ProductEntity entity){
        return productRepository.save(entity);
    }

    private ProductEntity getUpdatedProduct(ProductEntity entity, ProductUpdateDto dto){
        if (dto.getBuyersOnlyReview() != null) entity.setBuyersOnlyReview(dto.getBuyersOnlyReview());
        if (dto.getIsAvailable() != null) entity.setIsAvailable(dto.getIsAvailable());
        if (dto.getIsCommentEnabled() != null) entity.setIsCommentEnabled(dto.getIsCommentEnabled());
        if (dto.getIsVoteEnabled() != null) entity.setIsVoteEnabled(dto.getIsVoteEnabled());
        return entity;
    }

    public HttpResponse searchProducts(String providerId, Boolean isAvailable, Double minPrice, Double maxPrice,
                                              String sort, Sort.Direction sortDirection, Integer pageNumber, Integer PageSize){
        return HttpResponse.create(SUCCESS_RESULT.getCode(), mongoTemplate.find(ProductQuery.createQuery(providerId,
                        isAvailable, minPrice, maxPrice, sort, sortDirection, pageNumber, PageSize),
                ProductFetchDto.class, PRODUCT_COLLECTION_NAME));
    }

}
