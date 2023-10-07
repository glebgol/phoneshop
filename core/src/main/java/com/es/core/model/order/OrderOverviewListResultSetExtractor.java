package com.es.core.model.order;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderOverviewListResultSetExtractor implements ResultSetExtractor<List<OrderOverview>> {
    @Override
    public List<OrderOverview> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<OrderOverview> orders = new ArrayList<>();

        while (resultSet.next()) {
            orders.add(getOrder(resultSet));
        }

        return orders;
    }

    private OrderOverview getOrder(ResultSet resultSet) throws SQLException {
        OrderOverview orderOverview = new OrderOverview();
        orderOverview.setOrderNumber(resultSet.getLong("orderId"));
        orderOverview.setFirstName(resultSet.getString("firstName"));
        orderOverview.setLastName(resultSet.getString("lastName"));
        orderOverview.setContactPhoneNo(resultSet.getString("contactPhoneNo"));
        orderOverview.setDeliveryAddress(resultSet.getString("deliveryAddress"));
        orderOverview.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
        orderOverview.setDate(new Date(resultSet.getTimestamp("dateTime").getTime()));

        String status = resultSet.getString("status");
        if (status == null) {
            orderOverview.setStatus(null);
        } else {
            orderOverview.setStatus(OrderStatus.valueOf(status));
        }
        return orderOverview;
    }
}
