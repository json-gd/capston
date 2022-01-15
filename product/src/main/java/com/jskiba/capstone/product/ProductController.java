package com.jskiba.capstone.product;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/id/{id}")
    public Optional<Product> getProducts(@PathVariable String id) {
        return handleHystrix(() -> productService.getProductFromCatalog(id))
                .map(this::setAvailability);
    }

    @GetMapping("/sku/{sku}")
    public List<Product> getProductsBySku(@PathVariable String sku) {
        return handleHystrix(() -> productService.getProductFromCatalogBySku(sku))
                .stream()
                .map(this::setAvailability)
                .collect(Collectors.toList());
    }

    private Product setAvailability(Product product) {
        handleHystrix(() -> productService.getProductFromInventory(product.getUniqId()))
                .map(ProductAvailabilityResponse::isAvailable)
                .ifPresent(product::setAvailable);
        return product;
    }

    private <T> T handleHystrix(Supplier<T> callback) {
        try {
            return callback.get();
        } catch (HystrixRuntimeException | HystrixBadRequestException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
