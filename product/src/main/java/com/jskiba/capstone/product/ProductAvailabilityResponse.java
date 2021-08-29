package com.jskiba.capstone.product;

import lombok.Data;

@Data
public class ProductAvailabilityResponse {
    String productId;
    boolean isAvailable;
}
