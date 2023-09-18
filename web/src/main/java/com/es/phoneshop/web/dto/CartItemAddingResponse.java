package com.es.phoneshop.web.dto;

import java.math.BigDecimal;

public class CartItemAddingResponse {
    private int cartItemsQuantity;
    private BigDecimal totalCost;
    private String resultMessage;

    public CartItemAddingResponse(String message) {
        this.resultMessage = message;
    }

    public CartItemAddingResponse(int cartItemsQuantity, BigDecimal cost, String message) {
        this.cartItemsQuantity = cartItemsQuantity;
        this.totalCost = cost;
        this.resultMessage = message;
    }

    public CartItemAddingResponse() {
    }

    public int getCartItemsQuantity() {
        return cartItemsQuantity;
    }

    public void setCartItemsQuantity(int cartItemsQuantity) {
        this.cartItemsQuantity = cartItemsQuantity;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
