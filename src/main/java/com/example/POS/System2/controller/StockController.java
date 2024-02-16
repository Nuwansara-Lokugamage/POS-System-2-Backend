package com.example.POS.System2.controller;

import com.example.POS.System2.dto.StockDTO;
import com.example.POS.System2.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.status;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;


    @GetMapping
    public List<StockDTO> getAllStock(){
        return stockService.getAllStock();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable Long id){
        StockDTO stockDTO=stockService.getStockById(id);
        if (stockDTO != null){
            return new ResponseEntity<>(stockDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<StockDTO> addStock(@RequestBody StockDTO stockDTO){

        StockDTO addedStock=stockService.addStock(stockDTO);
        return new ResponseEntity<>(addedStock,HttpStatus.OK);



    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable Long id,@RequestBody StockDTO updatedStockDTO){
        StockDTO updatedStock = stockService.updateStock(id,updatedStockDTO);
        if(updatedStock!= null) {
            return new ResponseEntity<>(updatedStock,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
