package com.coderhouse.FacturacionPungitore.service;

import com.coderhouse.FacturacionPungitore.model.*;
import com.coderhouse.FacturacionPungitore.model.dto.InvoiceDTO;
import com.coderhouse.FacturacionPungitore.model.dto.InvoiceDetailDTO;
import com.coderhouse.FacturacionPungitore.model.dto.ProductDTO;
import com.coderhouse.FacturacionPungitore.repository.InvoiceRepository;
import com.coderhouse.FacturacionPungitore.request.InvoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    public Invoice crearFactura(InvoiceRequest solicitudFactura) throws Exception {
        Double precioTotal = 0.0d;
        List<InvoiceDetail> detallesFactura = new ArrayList<>();
        List<Product> productosParaActualizar = new ArrayList<>();

        Optional<Client> cliente = clientService.obtenerCliente(solicitudFactura.getClientId());

        if (cliente.isEmpty()) {
            throw new Exception("Cliente con id " + solicitudFactura.getClientId() + " inexsistente.");
        }

        for (ProductDTO productoSolicitado : solicitudFactura.getProductList()) {
            Optional<Product> posibleProductoObtenido = productService.obtenerProducto(productoSolicitado.getProductId());

            if (posibleProductoObtenido.isEmpty()) {
                throw new Exception("Producto con id " + productoSolicitado.getProductId() + " inexsistente");
            }

            Product productoObtenido = posibleProductoObtenido.get();

            if (productoSolicitado.getQuantity() > productoObtenido.getStock()) {
                throw new Exception("No hay suficiente stock para el producto con id "+ productoSolicitado.getProductId() + ". Stock disponible: " +  productoObtenido.getStock() + ". Cantidad solicitada: " + productoSolicitado.getQuantity());
            }

            precioTotal += productoObtenido.getPrice() * productoSolicitado.getQuantity();

            InvoiceDetail detalleFactura = new InvoiceDetail();
            detalleFactura.setProductId(productoObtenido);
            detalleFactura.setPrice(productoObtenido.getPrice());
            detalleFactura.setQuantity(productoSolicitado.getQuantity());
            detallesFactura.add(detalleFactura);

            productoObtenido.setStock(productoObtenido.getStock() - productoSolicitado.getQuantity());
            productosParaActualizar.add(productoObtenido);
        }

        Invoice facturaGenerada = new Invoice();
        facturaGenerada.setDate(new Date());
        facturaGenerada.setClientId(cliente.get());
        facturaGenerada.setTotal(precioTotal);

        Invoice factura = invoiceRepository.save(facturaGenerada);

        for (InvoiceDetail detalleFactura : detallesFactura) {
            detalleFactura.setInvoiceId(factura);
            invoiceDetailService.crearDetalleFactura(detalleFactura);
        }

        for (Product productoParaActualizar : productosParaActualizar) {
            productService.actualizarProducto(productoParaActualizar);
        }

        return factura;
    }

    public InvoiceDTO obtenerFactura(int id) {
        Optional<Invoice> factura = invoiceRepository.findById(id);

        if (factura.isEmpty()) {
            return null;
        }

        List<InvoiceDetail> detallesFacturaObtenidos = invoiceDetailService.obtenerDetallesFactura(factura.get());
        List<InvoiceDetailDTO> detallesFactura = new ArrayList<>();

        for (InvoiceDetail detalle : detallesFacturaObtenidos) {
            InvoiceDetailDTO detalleFactura = new InvoiceDetailDTO(detalle.getProduct().getTitle(), detalle.getProduct().getDescription(), detalle.getProduct().getCode(), detalle.getProduct().getPrice(), detalle.getQuantity());
            detallesFactura.add(detalleFactura);
        }

        return new InvoiceDTO(factura.get().getId(), factura.get().getClient().getId(), factura.get().getDate(), factura.get().getTotal(), detallesFactura);
    }

    public List<InvoiceDTO> obtenerFacturasPorIdCliente(int clientId) {
        Optional<Client> cliente = clientService.obtenerCliente(clientId);

        if (cliente.isPresent()) {
            List<Invoice> facturasObtenidas = invoiceRepository.findByClient(cliente.get());

            if (facturasObtenidas.size() == 0) {
                return new ArrayList<>();
            }
            List<InvoiceDTO> facturas = new ArrayList<>();

            for (Invoice factura : facturasObtenidas) {
                List<InvoiceDetail> detallesFacturaObtenidos = invoiceDetailService.obtenerDetallesFactura(factura);
                List<InvoiceDetailDTO> detallesFactura = new ArrayList<>();

                for (InvoiceDetail detalle : detallesFacturaObtenidos) {
                    InvoiceDetailDTO detalleFactura = new InvoiceDetailDTO(detalle.getProduct().getTitle(), detalle.getProduct().getDescription(), detalle.getProduct().getCode(), detalle.getProduct().getPrice(), detalle.getQuantity());
                    detallesFactura.add(detalleFactura);
                }

                facturas.add(new InvoiceDTO(factura.getId(), factura.getClient().getId(), factura.getDate(), factura.getTotal(), detallesFactura));
            }

            return facturas;
        }

        return new ArrayList<>();
    }

}
