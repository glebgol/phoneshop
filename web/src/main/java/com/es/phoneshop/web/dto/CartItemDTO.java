package com.es.phoneshop.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItemDTO {
    private Integer phoneId;

    @NotNull
    @Min(value = 1, message = "Quantity should be positive integer")
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
