package com.pos.iduka.service;

import com.pos.iduka.model.db.Product;
import com.pos.iduka.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product createNewProduct(Product product){
        return productRepository.save(product);
    }
}
