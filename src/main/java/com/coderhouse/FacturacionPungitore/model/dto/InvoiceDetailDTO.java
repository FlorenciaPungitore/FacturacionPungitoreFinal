package com.coderhouse.FacturacionPungitore.model.dto;

import com.coderhouse.FacturacionPungitore.model.Product;

public class InvoiceDetailDTO {
    private String title;
    private String description;
    private String code;
    private Double price;
    private Integer quantity;

    public InvoiceDetailDTO(String title, String description, String code, Double price, Integer quantity) {
        this.title = title;
        this.description = description;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InvoiceDetailDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
