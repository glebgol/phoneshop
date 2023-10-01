package com.es.core.model.order;

import java.util.Optional;
import java.util.UUID;

public interface OrderDao {
    Optional<Order> get(Long id);
    Optional<Order> get(UUID secureId);
    void save(final Order order);
}
