package com.coderhouse.FacturacionPungitore.service;

import com.coderhouse.FacturacionPungitore.model.Product;
import com.coderhouse.FacturacionPungitore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product guardarProducto(Product product) throws Exception {
        if (product.getTitle() == null || product.getTitle().isBlank()) {
            throw new Exception("El campo TITLE no puede estar vacio.");
        }

        if (product.getDescription() == null || product.getDescription().isBlank()) {
            throw new Exception("El campo DESCRIPTION no puede estar vacio.");
        }

        if (product.getCode() == null || product.getCode().isBlank()) {
            throw new Exception("El campo CODE no puede estar vacio.");
        } else {
            try {
                Integer.parseInt(product.getCode());
            } catch (NumberFormatException e) {
                throw new Exception("El campo CODE debe ser un numero.");
            }
        }

        if (product.getPrice() == null) {
            throw new Exception("El campo PRICE no puede estar vacio.");
        }

        if (product.getStock() == null) {
            throw new Exception("El campo STOCK no puede estar vacio.");
        }

        return productRepository.save(product);
    }

    public Product actualizarProducto(Product product) throws Exception {
        Optional<Product> productoObtenido = obtenerProducto(product.getId());
        if (productoObtenido.isPresent()) {
            if (product.getTitle() == null || product.getTitle().isBlank()) {
                product.setTitle(productoObtenido.get().getTitle());
            }

            if (product.getDescription() == null || product.getDescription().isBlank()) {
                product.setDescription(productoObtenido.get().getDescription());
            }

            if (product.getCode() == null || product.getCode().isBlank()) {
                product.setCode(productoObtenido.get().getCode());
            } else {
                try {
                    Integer.parseInt(product.getCode());
                } catch (NumberFormatException e) {
                    throw new Exception("El campo CODE debe ser un numero.");
                }
            }

            if (product.getPrice() == null) {
                product.setPrice(productoObtenido.get().getPrice());
            }

            if (product.getStock() == null) {
                product.setStock(productoObtenido.get().getStock());
            }

            return productRepository.save(product);
        } else {
            throw new Exception("Producto con id "+ product.getId() + " inexsistente.");
        }
    }

    public Optional<Product> obtenerProducto(int id) {
        return productRepository.findById(id);
    }

    public List<Product> obtenerProductos() {
        return productRepository.findAll();
    }

    public void eliminarProducto(Product product) {
        productRepository.delete(product);
    }

}
