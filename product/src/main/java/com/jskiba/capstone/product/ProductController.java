package com.jskiba.capstone.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/id/{id}")
    public Product getProducts(@PathVariable String id) {
        Product product = productService.getProductFromCatalog(id);
        product.setAvailable(productService.getProductFromInventory(id).isAvailable);
        return product;
    }

    @GetMapping("/sku/{sku}")
    public List<Product> getProductsBySku(@PathVariable String sku) {
        List<Product> products = productService.getProductFromCatalogBySku(sku);
        products.forEach(product -> product.setAvailable(productService.getProductFromInventory(product.getUniqId()).isAvailable));
        return products;
    }
}
