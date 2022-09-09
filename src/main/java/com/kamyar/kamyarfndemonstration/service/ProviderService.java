package com.kamyar.kamyarfndemonstration.service;

import com.kamyar.kamyarfndemonstration.dto.request.provider.ProviderRegistrationDto;
import com.kamyar.kamyarfndemonstration.db.entity.ProviderEntity;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.db.repository.ProviderRepository;
import com.kamyar.kamyarfndemonstration.exception.ProviderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.kamyar.kamyarfndemonstration.enums.ResultMessage.*;
import static com.kamyar.kamyarfndemonstration.enums.Role.PRODUCT_PROVIDER;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private UserService userService;

    /**
     * Registers a provider user.
     */
    public HttpResponse registerProvider(ProviderRegistrationDto dto){
        userService.validateNewUser(dto.getUsername(), dto.getPhoneNumber());
        providerRepository.save(getProviderFromDto(dto, userService.register(dto, PRODUCT_PROVIDER).getId()));
        return HttpResponse.create(SUCCESS_RESULT.getCode(), "Provider was successfully registered");
    }

    /**
     * Converts dto into a provider entity and the userId of
     * the provider must be given.
     */
    private ProviderEntity getProviderFromDto(ProviderRegistrationDto dto, String userId){
        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setId(null);
        providerEntity.setUserId(userId);
        providerEntity.setCorporationName(dto.getCorporationName());
        providerEntity.setLastAddedProduct(null);
        return providerEntity;
    }

    /**
     * Fetches the provider in db specified by its id.
     *
     */
    public ProviderEntity getProviderById(String id){
        return providerRepository.findById(id).orElseThrow(() -> new ProviderException(PROVIDER_WAS_NOT_FOUND));
    }

    /**
     * This method updates a provider entity's
     * last added product date.
     */
    public ProviderEntity updateProviderLastAddedDate(ProviderEntity entity){
         return providerRepository.save(entity);
    }


}
