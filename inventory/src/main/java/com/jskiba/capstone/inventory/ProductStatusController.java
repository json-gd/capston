package com.jskiba.capstone.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/products")
public class ProductStatusController {
    private final ProductStatusRepository repository;
    private final CatalogService catalogService;
    private final Random random = new Random();

    @Autowired
    public ProductStatusController(ProductStatusRepository repository, CatalogService catalogService) {
        this.repository = repository;
        this.catalogService = catalogService;
    }

    @GetMapping(path = "/id/{id}")
    public Optional<ProductStatus> productStatus(@PathVariable String id) throws InterruptedException {
//        Thread.sleep(random.nextInt(800)); // Simulate delays for CB pattern testing.
        this.catalogService.getProduct(id); // Add to have something to track by zipkin.
        return repository.findById(id);
    }
}
