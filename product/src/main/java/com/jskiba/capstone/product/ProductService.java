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

    @HystrixCommand(fallbackMethod = "handleCatalogServiceUnavailabilityId", commandKey = "product-service")
    Optional<Product> getProductFromCatalog(String id) throws ResponseStatusException {
        ResponseEntity<Product> itemResponseEntity =
                restTemplate.getForEntity("http://catalog/api/products/{id}",
                        Product.class,
                        id);
        return Optional.ofNullable(itemResponseEntity.getBody());
    }

    @HystrixCommand(fallbackMethod = "handleCatalogServiceUnavailabilitySku", commandKey = "product-service")
    List<Product> getProductFromCatalogBySku(String sku) {
        ResponseEntity<Product[]> itemResponseEntity =
                restTemplate.getForEntity("http://catalog/api/products/sku/{sku}",
                        Product[].class,
                        sku);
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(itemResponseEntity.getBody());
        }
        return List.of();
    }


    @HystrixCommand(fallbackMethod = "handleInventoryServiceUnavailability", commandKey = "inventory-service")
    Optional<ProductAvailabilityResponse> getProductFromInventory(String id) {
        ResponseEntity<ProductAvailabilityResponse> itemResponseEntity =
                restTemplate.getForEntity("http://inventory/api/products/id/{id}",
                        ProductAvailabilityResponse.class,
                        id);
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            return Optional.of(itemResponseEntity.getBody());
        }
        return Optional.empty();
    }

    Optional<Product> handleCatalogServiceUnavailabilityId(String id) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Catalog service is down or unreachable");
    }

    List<Product> handleCatalogServiceUnavailabilitySku(String sku, Throwable e) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Catalog service is down or unreachable");
    }

    Optional<ProductAvailabilityResponse> handleInventoryServiceUnavailability(String id, Throwable e) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Inventory service is down or unreachable");
    }

}
