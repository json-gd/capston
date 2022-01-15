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
    private final Random random = new Random();

    @Autowired
    public ProductStatusController(ProductStatusRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/id/{id}")
    public Optional<ProductStatus> productStatus(@PathVariable String id) throws InterruptedException {
        Thread.sleep(random.nextInt(800));
        return repository.findById(id);
    }
}
