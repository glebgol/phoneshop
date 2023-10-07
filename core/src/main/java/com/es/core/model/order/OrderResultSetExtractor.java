package com.es.core.model.order;

import com.es.core.model.phone.Phone;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Component
public class OrderResultSetExtractor implements ResultSetExtractor<Order> {
    @Override
    public Order extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Order order = null;

        if (resultSet.next()) {
            order = getOrder(resultSet);
            addItemToOrder(resultSet, order);

            while (resultSet.next()) {
                addItemToOrder(resultSet, order);
            }
        }
        return order;
    }

    private void addItemToOrder(ResultSet resultSet, Order order) throws SQLException {
        order.getOrderItems().add(getOrderItem(resultSet, order));
    }

    private OrderItem getOrderItem(ResultSet resultSet, Order order) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(resultSet.getLong("quantity"));
        orderItem.setId(resultSet.getLong("orderItemId"));
        orderItem.setOrder(order);
        orderItem.setPhone(new Phone());
        orderItem.getPhone().setId(resultSet.getLong("phoneId"));
        return orderItem;
    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setDate(new Date(resultSet.getTimestamp("dateTime").getTime()));
        order.setOrderItems(new ArrayList<>());
        order.setId(resultSet.getLong("orderId"));
        order.setSecureId(resultSet.getObject("secureId", java.util.UUID.class));
        order.setSubtotal(resultSet.getBigDecimal("subTotal"));
        order.setDeliveryPrice(resultSet.getBigDecimal("deliveryPrice"));
        order.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
        order.setFirstName(resultSet.getString("firstName"));
        order.setLastName(resultSet.getString("lastName"));
        order.setDeliveryAddress(resultSet.getString("deliveryAddress"));
        order.setContactPhoneNo(resultSet.getString("contactPhoneNo"));
        order.setAdditionalInfo(resultSet.getString("additionalInfo"));
        String status = resultSet.getString("status");

        if (status == null) {
            order.setStatus(null);
        } else {
            order.setStatus(OrderStatus.valueOf(status));
        }
        return order;
    }
}
