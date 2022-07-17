package com.timf.teamfreash.service;

import com.timf.teamfreash.model.Provider;
import com.timf.teamfreash.model.exception.ProviderNotFoundException;
import com.timf.teamfreash.repository.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProviderService {
    ProviderRepo providerRepo;

    @Autowired
    public ProviderService(ProviderRepo providerRepo) {
        this.providerRepo = providerRepo;
    }

    public Provider createProvider(Provider provider) {
        return providerRepo.save(provider);
    }

    public Provider getProviderFromId(long id) {
        return providerRepo.findById(id).orElseThrow(() ->
                new ProviderNotFoundException(id));
    }

    public Provider getProviderFromUserId(String userId) {
        return providerRepo.findByUserId(userId).orElseThrow(() ->
                new ProviderNotFoundException(userId));
    }

    @Transactional
    public Provider deleteProviderFromId(long id) {
        Provider provider = getProviderFromId(id);
        providerRepo.delete(provider);
        return provider;
    }
}
