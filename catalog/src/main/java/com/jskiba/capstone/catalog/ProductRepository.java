package com.jskiba.capstone.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product getByUniqId(String uniqId);
}
