package com.example.POS.System2.repository;


import com.example.POS.System2.dto.StockDTO;
import com.example.POS.System2.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
