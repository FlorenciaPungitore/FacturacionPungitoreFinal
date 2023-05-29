package com.coderhouse.FacturacionPungitore.service;

import com.coderhouse.FacturacionPungitore.model.Client;
import com.coderhouse.FacturacionPungitore.model.Product;
import com.coderhouse.FacturacionPungitore.repository.ClientRepository;
import com.coderhouse.FacturacionPungitore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product crearProducto(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> obtenerProducto(int id) {
        return productRepository.findById(id);
    }

}
