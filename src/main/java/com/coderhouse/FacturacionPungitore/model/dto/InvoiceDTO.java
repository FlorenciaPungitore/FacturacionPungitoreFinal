package com.coderhouse.FacturacionPungitore.model.dto;

import java.util.Date;
import java.util.List;

public class InvoiceDTO {

    private int invoiceId;
    private int clientId;
    private Date createdAt;
    private Double total;
    private List<InvoiceDetailDTO> details;

    public InvoiceDTO(int invoiceId, int clientId, Date createdAt, Double total,  List<InvoiceDetailDTO> details) {
        this.invoiceId = invoiceId;
        this.clientId = clientId;
        this.createdAt = createdAt;
        this.total = total;
        this.details = details;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<InvoiceDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetailDTO> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "invoiceId=" + invoiceId +
                ", clientId=" + clientId +
                ", createdAt=" + createdAt +
                ", total=" + total +
                ", details=" + details +
                '}';
    }
}
