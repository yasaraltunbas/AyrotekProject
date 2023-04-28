package com.project.ayrotek.controller;

import com.project.ayrotek.entity.Product;
import com.project.ayrotek.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
   public ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProduct")

    public List<Product> getAllProduct(){
        return this.productService.getAll();
    }
    @PostMapping("/addOneProduct")
    public Product addProduct(@RequestBody Product product){
        return this.productService.addProduct(product);
    }
    @DeleteMapping("/{deleteProductById}")

    public Product deleteProductById(int id){
        return this.productService.deleteOneProduct(id);
    }
}
