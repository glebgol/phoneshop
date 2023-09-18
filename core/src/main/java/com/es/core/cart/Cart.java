package com.es.core.cart;

import java.math.BigDecimal;
import java.util.List;

public interface Cart {
    List<CartItem> getItems();
    void setItems(List<CartItem> items);
    int getTotalQuantity();
    void setTotalQuantity(int totalQuantity);
    BigDecimal getTotalCost();
    void setTotalCost(BigDecimal totalCost);
}
