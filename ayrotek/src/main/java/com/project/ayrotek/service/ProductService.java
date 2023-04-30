package com.project.ayrotek.service;

import com.project.ayrotek.entity.Product;
import com.project.ayrotek.repository.ProductJpaRepository;
import com.project.ayrotek.result.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public ProductJpaRepository productJpaRepository;

    public ProductService(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    public DataResult<List<Product>>  getAll() {

        return new SuccessDataResult<List<Product>>
                (this.productJpaRepository.findAll(),"Tüm Ürünler Listelendi");    }

    public Result addProduct(Product product) {
        this.productJpaRepository.save(product);
        return new SuccessResult("Ürün Eklendi.");
    }

    public Result deleteProduct(int productId, int clientId) throws Exception {
        Optional<Product> product = productJpaRepository.findById(productId);

        if(product.isPresent()&&product.get().getClientId()==clientId) {

            productJpaRepository.deleteById(productId);
        }
        else
        {
            return new ErrorResult("Müşteri yalnızca kendine ait ürünleri kaldırabilir.");
        }
        boolean isDeleted = !productJpaRepository.existsById(productId);

        if (isDeleted){
            return new SuccessResult(""+ productId + " id'li ürün kaldırıldı.");
        }

        return new ErrorResult("Ürün kaldırılamadı.");


    }

    public Result updateProduct( Product newProduct) {

        Optional<Product> product = productJpaRepository.findById(newProduct.getProductId());
        if (product.isPresent()&&product.get().getClientId()==newProduct.getClientId()){
            Product foundProduct = product.get();
            foundProduct.setProductName(newProduct.getProductName());
            foundProduct.setProductNumber(newProduct.getProductNumber());
            foundProduct.setProductPrice(newProduct.getProductPrice());
            productJpaRepository.save(foundProduct);
            return new SuccessResult("Ürün başarıyla değiştirildi.");

        }
        else
            return new ErrorResult("Müşteri yalnızca kendine ait ürünleri değiştirilebilir.");
    }
}
