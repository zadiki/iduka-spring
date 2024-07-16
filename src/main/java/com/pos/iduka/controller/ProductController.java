package com.pos.iduka.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.iduka.model.db.Product;
import com.pos.iduka.repository.ProductRepository;
import com.pos.iduka.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
   private ProductService productService;
    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<Product> createNewProduct(@RequestBody Product product) throws JsonProcessingException {
        var createdProduct= productService.createNewProduct(product);
        log.info("result body "+objectMapper.writeValueAsString(createdProduct));
        return new ResponseEntity<>(createdProduct,HttpStatus.OK);
    }
}
