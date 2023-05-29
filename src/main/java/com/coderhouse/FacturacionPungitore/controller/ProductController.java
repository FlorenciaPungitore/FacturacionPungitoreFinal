package com.coderhouse.FacturacionPungitore.controller;

import com.coderhouse.FacturacionPungitore.ResponseHandler;
import com.coderhouse.FacturacionPungitore.model.Product;
import com.coderhouse.FacturacionPungitore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Creacion de Producto
    @PostMapping
    public ResponseEntity<Object> crearProducto(@RequestBody Product product) {
        try {
            Product productoeGuardado = productService.crearProducto(product);
            return ResponseHandler.generateResponse("Producto creado correctamente", HttpStatus.CREATED, productoeGuardado);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    //Obtencion de Productos
    @GetMapping(path = "{id}")
    public ResponseEntity<Object> obtenerProducto(@PathVariable int id) {
        try {
            Optional<Product> productoObtenido = productService.obtenerProducto(id);
            if (productoObtenido.isPresent()) {
                return ResponseHandler.generateResponse("Producto encontrado correctamente", HttpStatus.OK, productoObtenido);
            } else {
                return ResponseHandler.generateResponse("Producto con id "+ id + " inexsistente.", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}