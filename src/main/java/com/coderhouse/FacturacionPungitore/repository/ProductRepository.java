package com.coderhouse.FacturacionPungitore.repository;

import com.coderhouse.FacturacionPungitore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

