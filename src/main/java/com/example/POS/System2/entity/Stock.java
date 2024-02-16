package com.example.POS.System2.entity;

import com.example.POS.System2.dto.StockDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "productID",nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;


}
