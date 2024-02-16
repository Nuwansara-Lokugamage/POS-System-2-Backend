package com.example.POS.System2.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockDTO {
    private Long id;
    private Long productID;
    private int quantity;

}
