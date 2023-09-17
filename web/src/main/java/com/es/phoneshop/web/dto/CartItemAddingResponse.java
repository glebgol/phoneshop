package com.es.phoneshop.web.dto;

import java.math.BigDecimal;

public class CartItemAddingResponse {
    private int cartItemsQuantity;
    private BigDecimal cost;
    private String resultMessage;

    public CartItemAddingResponse(String message) {
        this.resultMessage = message;
    }

    public CartItemAddingResponse(int cartItemsQuantity, BigDecimal cost, String message) {
        this.cartItemsQuantity = cartItemsQuantity;
        this.cost = cost;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
