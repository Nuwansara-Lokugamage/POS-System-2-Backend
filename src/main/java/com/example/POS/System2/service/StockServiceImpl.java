package com.example.POS.System2.service;

import com.example.POS.System2.dto.StockDTO;
import com.example.POS.System2.entity.Product;
import com.example.POS.System2.entity.Stock;
import com.example.POS.System2.repository.ProductRepository;
import com.example.POS.System2.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService{
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<StockDTO> getAllStock(){
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StockDTO getStockById(Long id){
        Optional<Stock> optionalStock=stockRepository.findById(id);
        return optionalStock.map(this::convertToDTO).orElse(null);
    }

   /* @Override
    public StockDTO addStock(StockDTO stockDTO){

       // Stock stock=convertToEntity(stockDTO);
        //stock=stockRepository.save(stock);
       // return convertToDTO(stock);

        Stock stock = new Stock();
        stock.setQuantity(stockDTO.getQuantity());

        // Fetch the associated Product from the database using productID
        Product product = productRepository.findById(stockDTO.getProductID())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + stockDTO.getProductID()));

        stock.setProduct(product);

        // Save the Stock entity to the database
        Stock savedStock = stockRepository.save(stock);

        // Convert the saved Stock entity to a DTO and return it
        return convertToDTO(savedStock);

    }
    */

    @Override
    public StockDTO addStock(StockDTO stockDTO) {
        try {
            if (stockDTO.getProductID() == null) {
                throw new IllegalArgumentException("Product ID must not be null");
            }

            // Fetch the associated Product from the database using productID
            Product product = productRepository.findById(stockDTO.getProductID())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + stockDTO.getProductID()));

            Stock stock = new Stock();
            stock.setQuantity(stockDTO.getQuantity());
            stock.setProduct(product);

            // Save the Stock entity to the database
            Stock savedStock = stockRepository.save(stock);

            // Convert the saved Stock entity to a DTO and return it
            return convertToDTO(savedStock);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            throw e; // Rethrow the exception for handling at the controller level or let it propagate
        }
    }





    @Override
    public StockDTO updateStock(Long id, StockDTO updatedStockDTO){
        Optional<Stock> optionalStock=stockRepository.findById(id);
        if(optionalStock.isPresent()){
            Stock existingStock=optionalStock.get();
            existingStock.setQuantity(updatedStockDTO.getQuantity());
            stockRepository.save(existingStock);
            return convertToDTO(existingStock);
        }else {
            return null;
        }
    }

    @Override
    public void deleteStock(Long id){
        stockRepository.deleteById(id);
    }

    private StockDTO convertToDTO(Stock stock){
        StockDTO stockDTO=new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setProductID(stock.getProduct().getProductID());
        stockDTO.setQuantity(stock.getQuantity());
        return stockDTO;
    }

    private Stock convertToEntity (StockDTO stockDTO){
        Stock stock=new Stock();
        stock.setQuantity(stockDTO.getQuantity());

        Long productId = stockDTO.getProductID();

        //Product product=productRepository.findById(stockDTO.getProductID()).orElse(null);
        //stock.setProduct(product);

        if (productId != null) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found"));
            stock.setProduct(product); // Set the found product in the Stock entity
        }



        return stock;
    }



}
