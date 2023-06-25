package com.coderhouse.FacturacionPungitore.controller;

import com.coderhouse.FacturacionPungitore.ResponseHandler;
import com.coderhouse.FacturacionPungitore.model.Product;
import com.coderhouse.FacturacionPungitore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Creacion de Producto
    @PostMapping
    public ResponseEntity<Object> guardarProducto(@RequestBody Product product) {
        try {
            Product productoeGuardado = productService.guardarProducto(product);
            return ResponseHandler.generateResponse("Producto creado correctamente", HttpStatus.CREATED, productoeGuardado);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> actualizarProducto(@PathVariable int id, @RequestBody Product product) {
        try {
            Optional<Product> productoObtenido = productService.obtenerProducto(id);
            if (productoObtenido.isPresent()) {
                product.setId(id);
                productService.actualizarProducto(product);
                return ResponseHandler.generateResponse("Producto actualizado correctamente", HttpStatus.OK, productoObtenido);
            } else {
                return ResponseHandler.generateResponse("Producto con id "+ id + " inexsistente.", HttpStatus.OK, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
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
                return ResponseHandler.generateResponse("Producto con id "+ id + " inexsistente.", HttpStatus.OK, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/products-list")
    public ResponseEntity<Object> obtenerProductos() {
        try {
            List<Product> productosObtenidos = productService.obtenerProductos();
            if (productosObtenidos.size() > 0) {
                return ResponseHandler.generateResponse("Productos encontrados correctamente", HttpStatus.OK, productosObtenidos);
            } else {
                return ResponseHandler.generateResponse("No hay productos almacenados.", HttpStatus.OK, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> eliminarProducto(@PathVariable int id) {
        try {
            Optional<Product> productoObtenido = productService.obtenerProducto(id);
            if (productoObtenido.isPresent()) {
                productService.eliminarProducto(productoObtenido.get());
                return ResponseHandler.generateResponse("Producto con id " + id + " eliminado correctamente", HttpStatus.OK, productoObtenido);
            } else {
                return ResponseHandler.generateResponse("Producto con id "+ id + " inexsistente.", HttpStatus.OK, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

}