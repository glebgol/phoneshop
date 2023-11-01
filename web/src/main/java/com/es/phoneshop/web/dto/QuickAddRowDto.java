package com.es.phoneshop.web.dto;

public class QuickAddRowDto {
    private Long quantity;
    private String model;

    public QuickAddRowDto(Long quantity, String model) {
        this.quantity = quantity;
        this.model = model;
    }

    public QuickAddRowDto() {
        model = "";
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
