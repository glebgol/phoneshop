package com.es.core.cart;

import com.es.core.model.phone.Phone;

public class CartItem {
    private Phone phone;
    private int quantity;

    public CartItem(Phone phone, int quantity) {
        this.phone = phone;
        this.quantity = quantity;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + phone +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public CartItem clone() {
        try {
            return (CartItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
