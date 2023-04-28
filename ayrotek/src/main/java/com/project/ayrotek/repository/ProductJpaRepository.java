package com.project.ayrotek.repository;

import com.project.ayrotek.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository <Product, Integer> {
}
