package com.coderhouse.FacturacionPungitore.repository;

import com.coderhouse.FacturacionPungitore.model.Invoice;
import com.coderhouse.FacturacionPungitore.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {

    public List<InvoiceDetail> findByInvoice(Invoice invoice);

}

