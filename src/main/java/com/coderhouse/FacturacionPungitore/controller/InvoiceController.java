package com.coderhouse.FacturacionPungitore.controller;

import com.coderhouse.FacturacionPungitore.ResponseHandler;
import com.coderhouse.FacturacionPungitore.model.*;
import com.coderhouse.FacturacionPungitore.model.dto.InvoiceDTO;
import com.coderhouse.FacturacionPungitore.request.InvoiceRequest;
import com.coderhouse.FacturacionPungitore.service.InvoiceDetailService;
import com.coderhouse.FacturacionPungitore.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @PostMapping
    public ResponseEntity<Object> generarFacturas(@RequestBody InvoiceRequest invoiceRequest) throws Exception {
        try {
            Invoice factura = invoiceService.crearFactura(invoiceRequest);
            return ResponseHandler.generateResponse("Factura generada correctamente.", HttpStatus.CREATED, factura);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> obtenerFactura(@PathVariable int id) {
        try {
            InvoiceDTO facturaObtenida = invoiceService.obtenerFactura(id);
            if (facturaObtenida != null) {
                return ResponseHandler.generateResponse("Factura encontrada correctamente", HttpStatus.OK, facturaObtenida);
            } else {
                return ResponseHandler.generateResponse("No hay facturas para el cliente con id " + id, HttpStatus.OK, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(path = "/client/{id}")
    public ResponseEntity<Object> obtenerFacturas(@PathVariable int id) {
        try {
            List<InvoiceDTO> facturasObtenidas = invoiceService.obtenerFacturasPorIdCliente(id);
            if (facturasObtenidas.size() > 0) {
                return ResponseHandler.generateResponse("Facturas encontradas correctamente", HttpStatus.OK, facturasObtenidas);
            } else {
                return ResponseHandler.generateResponse("No hay facturas para el cliente con id " + id, HttpStatus.OK, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}