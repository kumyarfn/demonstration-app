package com.kamyar.kamyarfndemonstration.util;

import com.kamyar.kamyarfndemonstration.exception.ProductException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static com.kamyar.kamyarfndemonstration.enums.Constants.*;
import static com.kamyar.kamyarfndemonstration.enums.ResultMessage.*;

@Getter @Setter
public class ProductQuery {

    private String providerId;

    private Double minPrice;

    private Double maxPrice;

    private String sort;

    private Sort.Direction sortDirection;

    private Integer pageNumber;

    private Integer pageSize;

    private Boolean isAvailable;

    private Query query;


    private ProductQuery(String providerId, Boolean isAvailable, Double minPrice, Double maxPrice, String sort, Sort.Direction sortDirection, Integer pageNumber, Integer pageSize) {
        this.providerId = providerId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.isAvailable = isAvailable;
        if (sort == null || sort.isEmpty()) throw new ProductException(PRODUCT_SEARCH_SORT_EXCEPTION);
        this.sort = sort;
        if (sortDirection == null) throw new ProductException(PRODUCT_SEARCH_SORT_DIRECTION_EXCEPTION);
        this.sortDirection = sortDirection;
        if (pageNumber == null) throw new ProductException(PRODUCT_SEARCH_PAGE_NUMBER_EXCEPTION);
        this.pageNumber = pageNumber;
        if (pageSize == null) throw new ProductException(PRODUCT_SEARCH_PAGE_SIZE_EXCEPTION);
        this.pageSize = pageSize;
        this.query = new Query().addCriteria(getCriteria(providerId, isAvailable, minPrice, maxPrice)).with(PageRequest.of(pageNumber, pageSize, sortDirection, sort));
    }

    private Criteria getCriteria(String providerId, Boolean isAvailable, Double minPrice, Double maxPrice) {
        Criteria criteria = new Criteria();
        if (isAvailable != null) criteria.and(IS_AVAILABLE_FIELD).is(isAvailable);
        if (providerId != null && !providerId.isEmpty()) criteria.and(PROVIDER_ID_FIELD).is(providerId);
        if (minPrice != null && maxPrice != null) criteria.and(PRICE_FIELD).gte(minPrice).and(PRICE_FIELD).lte(maxPrice);
        if (minPrice != null) criteria.and(PRICE_FIELD).gte(minPrice);
        if (maxPrice != null) criteria.and(PRICE_FIELD).lte(maxPrice);
        return criteria;
    }

    public static Query createQuery(String providerId, Boolean isAvailable, Double minPrice, Double maxPrice, String sort,
                                    Sort.Direction sortDirection, Integer pageNumber, Integer pageSize){
        return new ProductQuery(providerId, isAvailable, minPrice, maxPrice, sort, sortDirection, pageNumber, pageSize).getQuery();
    }


}
