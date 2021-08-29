package com.jskiba.capstone.product;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand()
    Product getProductFromCatalog(String id) {
        ResponseEntity<Product> itemResponseEntity =
                restTemplate.getForEntity("http://catalog/api/products/{id}",
                        Product.class,
                        id);
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            return itemResponseEntity.getBody();
        }
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }


    @HystrixCommand()
    List<Product> getProductFromCatalogBySku(String sku) {
        ResponseEntity<Product[]> itemResponseEntity =
                restTemplate.getForEntity("http://catalog/api/products/sku/{sku}",
                        Product[].class,
                        sku);
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(itemResponseEntity.getBody());
        }
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }


    @HystrixCommand()
    ProductAvailabilityResponse getProductFromInventory(String id) {
        ResponseEntity<ProductAvailabilityResponse> itemResponseEntity =
                restTemplate.getForEntity("http://inventory/api/products/id/{id}",
                        ProductAvailabilityResponse.class,
                        id);
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            return itemResponseEntity.getBody();
        }
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
