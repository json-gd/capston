package com.jskiba.capstone.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductStatus {
    @Column @Id String productId;
    @Column boolean isAvailable;
}
