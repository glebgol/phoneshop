package com.es.core.order;

import com.es.core.cart.Cart;
import com.es.core.cart.CartItem;
import com.es.core.cart.CartService;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderDao;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;
import com.es.core.model.phone.dao.StockDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Value("${delivery.price}")
    private BigDecimal deliveryPrice;
    private final OrderDao orderDao;
    private final CartService cartService;
    private final StockDao stockDao;

    public OrderServiceImpl(OrderDao orderDao, CartService cartService, StockDao stockDao) {
        this.orderDao = orderDao;
        this.cartService = cartService;
        this.stockDao = stockDao;
    }

    @Override
    public Order createOrder(Cart cart) {
        Order order = new Order();
        order.setSubtotal(cart.getTotalCost());
        order.setDeliveryPrice(deliveryPrice);
        order.setTotalPrice(cart.getTotalCost().add(deliveryPrice));
        order.setOrderItems(cart.getItems().stream().map(cartItem -> mapToCartItem(order, cartItem))
                .collect(Collectors.toList()));
        order.setStatus(OrderStatus.NEW);

        return order;
    }

    @Override
    @Transactional
    public void placeOrder(Order order) throws OutOfStockException {
        cartService.removeOutOfStockItems();

        orderDao.save(order);
        order.getOrderItems().forEach(orderItem -> stockDao.reduceStock(orderItem.getPhone().getId(),
                orderItem.getQuantity()));
        cartService.clearCart();
    }

    private static OrderItem mapToCartItem(Order order, CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setPhone(cartItem.getPhone());
        orderItem.setQuantity((long) cartItem.getQuantity());
        return orderItem;
    }
}
