package com.jskiba.capstone.product;

import lombok.Data;

import java.util.List;

@Data
public class ListProductResponse {
    List<Product> productList;
}
