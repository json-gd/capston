package com.jskiba.capstone.catalog;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Column @Id private String uniqId;
    @Column String sku;
    @Column String nameTitle;
    @Column String description;
    @Column String listPrice;
    @Column String salePrice;
    @Column String category;
    @Column String categoryTree;
    @Column String averageProductRating;
    @Column String productUrl;
    @Column String productImageUrls;
    @Column String brand;
    @Column String totalNumberReviews;
    @Column String reviews;
}
