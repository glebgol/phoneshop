package com.es.core.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class HttpSessionCart implements Cart {
    private List<CartItem> items;
    private int totalQuantity;
    private BigDecimal totalCost;

    public HttpSessionCart() {
        this.items = new ArrayList<>();
        totalCost = new BigDecimal(0);
    }

    public HttpSessionCart(List<CartItem> items, int totalQuantity, BigDecimal totalCost) {
        this.items = items;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
