package com.es.phoneshop.web.dto;

public class CartItemDTO {
    private Integer phoneId;

    private Integer quantity;

    public CartItemDTO(Integer productId, Integer quantity) {
        this.phoneId = productId;
        this.quantity = quantity;
    }

    public CartItemDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }
}
