package com.coderhouse.FacturacionPungitore.model.dto;

public class ProductDTO {
    private Integer productId;
    private Integer quantity;

    public ProductDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
