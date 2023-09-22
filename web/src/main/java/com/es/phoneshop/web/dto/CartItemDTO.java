package com.es.phoneshop.web.dto;

public class CartItemDTO {
    private Long phoneId;

    private Long quantity;

    public CartItemDTO(Long productId, Long quantity) {
        this.phoneId = productId;
        this.quantity = quantity;
    }

    public CartItemDTO() {
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }
}
