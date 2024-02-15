package com.example.POS.System2.service;

import com.example.POS.System2.dto.ProductDTO;
import com.example.POS.System2.entity.Product;
import com.example.POS.System2.repository.ProductRepository;
import com.example.POS.System2.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public String saveProduct(ProductDTO productDTO){
        if(productRepository.existsById(productDTO.getProductID())){
            return VarList.RSP_DUPLICATED;
        }else {
            productRepository.save(modelMapper.map(productDTO, Product.class));
            return VarList.RSP_SUCCESS;

        }
    }

    public String updateProduct(ProductDTO productDTO){
        if (productRepository.existsById(productDTO.getProductID())){
            productRepository.save(modelMapper.map(productDTO,Product.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<ProductDTO> getAllProducts(){
        List<Product> productList=productRepository.findAll();
        return modelMapper.map(productList, new TypeToken<ArrayList<ProductDTO>>(){

        }.getType());

    }

    public ProductDTO searchProduct(int productID){
        if (productRepository.existsById(productID)){
            Product product=productRepository.findById(productID).orElse(null);
            return modelMapper.map(product, ProductDTO.class);
        }else {
            return null;
        }
    }

    public String deleteProduct(int productID){
        if (productRepository.existsById(productID)){
            productRepository.deleteById(productID);
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
