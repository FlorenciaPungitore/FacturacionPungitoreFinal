package com.coderhouse.FacturacionPungitore.request;

import com.coderhouse.FacturacionPungitore.model.dto.ProductDTO;

import java.util.List;

public class InvoiceRequest {
    private Integer clientId;
    private List<ProductDTO> productList;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProducts(List<ProductDTO> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "InvoiceRequest{" +
                "clientId=" + clientId +
                ", productList=" + productList +
                '}';
    }
}
