package com.es.core.model.order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderDao {
    Optional<Order> get(Long id);
    Optional<Order> get(UUID secureId);
    List<OrderOverview> getOrders();
    void save(final Order order);
    void setOrderStatus(Long orderId, OrderStatus orderStatus);
}
