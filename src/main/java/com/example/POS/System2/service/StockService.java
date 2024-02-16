package com.example.POS.System2.service;

import com.example.POS.System2.dto.StockDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface StockService {
    List<StockDTO> getAllStock();
    StockDTO getStockById(Long id);
    StockDTO addStock(StockDTO stockDTO);
    StockDTO updateStock(Long id,StockDTO updatedStockDTO);
    void deleteStock(Long id);
}
