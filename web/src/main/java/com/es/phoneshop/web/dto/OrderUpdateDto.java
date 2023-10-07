package com.es.phoneshop.web.dto;

import com.es.core.model.order.OrderStatus;

public class OrderUpdateDto {
    private OrderStatus orderStatus;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
