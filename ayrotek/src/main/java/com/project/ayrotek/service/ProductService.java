package com.project.ayrotek.service;

import com.project.ayrotek.entity.Product;
import com.project.ayrotek.repository.ProductJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public ProductJpaRepository productJpaRepository;

    public ProductService(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    public List<Product> getAll() {
        return this.productJpaRepository.findAll();
    }

    public Product addProduct(Product product) {
        return this.productJpaRepository.save(product);
    }

    public Product deleteOneProduct(int id) {
        Optional<Product> product = productJpaRepository.findById(id);
        if (product.isPresent()){
            productJpaRepository.deleteById(id);

        }
        boolean isDeleted =! productJpaRepository.existsById(id);
    }
}
