package com.jskiba.capstone.product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Product {
    String uniqId;
    String sku;
    String nameTitle;
    String description;
    String listPrice;
    String salePrice;
    String category;
    String categoryTree;
    String averageProductRating;
    String productUrl;
    String productImageUrls;
    String brand;
    String totalNumberReviews;
    String reviews;
    boolean isAvailable;
}
