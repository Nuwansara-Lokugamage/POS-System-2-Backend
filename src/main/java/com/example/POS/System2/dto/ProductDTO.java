package com.example.POS.System2.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private Long productID;

    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Double productPrice;
}
