package com.jskiba.capstone.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductStatusController {
    private final ProductStatusRepository repository;

    @Autowired
    public ProductStatusController(ProductStatusRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/id/{id}")
    public Optional<ProductStatus> productStatus(@PathVariable String id) {
        return repository.findById(id);
    }
}
