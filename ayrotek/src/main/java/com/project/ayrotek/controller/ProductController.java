package com.project.ayrotek.controller;

import com.project.ayrotek.entity.Product;
import com.project.ayrotek.result.DataResult;
import com.project.ayrotek.result.Result;
import com.project.ayrotek.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

   public ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProduct")

    public DataResult<List<Product>>  getAllProduct(){
        return this.productService.getAll();
    }
    @PostMapping("/addOneProduct")
    public Result addProduct(@RequestBody Product product){
        return this.productService.addProduct(product);
    }
    @DeleteMapping("/{deleteProductById}")

    public Result deleteProductById(int productId, int clientId) throws Exception {
        return this.productService.deleteProduct(productId,clientId);
    }
    @PutMapping("/{updateProductById}")

    public Result updateProduct(@RequestBody Product newProduct){

        return  this.productService.updateProduct(newProduct);
    }










}
