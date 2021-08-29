package com.jskiba.capstone.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product getByUniqId(String uniqId);

    List<Product> findBySku(String sku);
}
