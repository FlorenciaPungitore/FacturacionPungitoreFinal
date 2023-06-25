package com.coderhouse.FacturacionPungitore.service;

import com.coderhouse.FacturacionPungitore.model.Invoice;
import com.coderhouse.FacturacionPungitore.model.InvoiceDetail;
import com.coderhouse.FacturacionPungitore.repository.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailService {

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetail crearDetalleFactura(InvoiceDetail invoice) {
        return invoiceDetailRepository.save(invoice);
    }

    public List<InvoiceDetail> obtenerDetallesFactura(Invoice factura) {
        return invoiceDetailRepository.findByInvoice(factura);
    }

}
