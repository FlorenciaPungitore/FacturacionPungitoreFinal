package com.coderhouse.FacturacionPungitore.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="client_id")
    private Client client;
    private Double total;
    private Date invoiceDate;

    //Getters y setters y override de toString
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClientId(Client client) {
        this.client = client;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return invoiceDate;
    }

    public void setDate(Date date) {
        this.invoiceDate = date;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", client=" + client +
                ", total=" + total +
                ", date=" + invoiceDate +
                '}';
    }
}
