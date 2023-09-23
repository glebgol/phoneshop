package com.es.phoneshop.web.dto;

import java.util.List;

public class CartDTO {
    private List<CartItemDTO> items;

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }
}
