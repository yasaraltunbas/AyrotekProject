package com.project.ayrotek.tester;

import com.project.ayrotek.entity.Product;
import com.project.ayrotek.repository.ProductJpaRepository;
import com.project.ayrotek.result.Result;
import com.project.ayrotek.result.SuccessResult;
import com.project.ayrotek.service.ProductService;
//import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AyrotekApplicationTest {

    @Autowired
    private ProductJpaRepository productJpaRepository;
    private ProductService productService;
    @BeforeEach
    public void setup(){
        productService = new ProductService(productJpaRepository);
    }

    @Test
    void testGetProduct() {
        Assert.assertTrue(productService.getAll().isSuccess());
    }
    @Test
   public void testAddProduct(){

        Product product = new Product();
        product.setProductId(2);
        product.setClientId(2);
        product.setProductName("Masa");
        product.setProductNumber(23);
        product.setProductPrice(50);
        productService.addProduct(product);


       Assertions.assertNotNull(productJpaRepository.findById(2).get());
    }
    @Test
    void testDeleteProduct() throws Exception {

        Product product = productJpaRepository.findById(1).get();
        Result result = productService.deleteProduct(product.getProductId(),product.getClientId());

        Assert.assertTrue(result.isSuccess());
    }
    @Test
    void testUpdateProduct(){


        Product product = productJpaRepository.findById(1).get();
        product.setProductName("sandalye");
        Result result = productService.updateProduct(product);

        product = productJpaRepository.findById(1).get();
        Assertions.assertEquals("sandalye", product.getProductName());

    }
}