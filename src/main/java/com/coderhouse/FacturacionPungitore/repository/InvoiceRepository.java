package com.coderhouse.FacturacionPungitore.repository;

import com.coderhouse.FacturacionPungitore.model.Client;
import com.coderhouse.FacturacionPungitore.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    public List<Invoice> findByClient(Client client);

}

