package com.jskiba.capstone.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogService {
    private final RestTemplate restTemplate;

    @Autowired
    public CatalogService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    void getProduct(String id) {
        restTemplate.getForObject("http://catalog/api/products/{id}", String.class, id);
    }
}
