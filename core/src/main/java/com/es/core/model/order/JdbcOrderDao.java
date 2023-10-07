package com.es.core.model.order;

import com.es.core.model.phone.dao.PhoneDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JdbcOrderDao implements OrderDao {
    private final JdbcTemplate jdbcTemplate;
    private final OrderResultSetExtractor orderResultSetExtractor;
    private final OrderOverviewListResultSetExtractor orderOverviewListResultSetExtractor;
    private final PhoneDao phoneDao;

    public JdbcOrderDao(JdbcTemplate jdbcTemplate, OrderResultSetExtractor orderResultSetExtractor,
                        OrderOverviewListResultSetExtractor orderOverviewListResultSetExtractor, PhoneDao phoneDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.orderResultSetExtractor = orderResultSetExtractor;
        this.orderOverviewListResultSetExtractor = orderOverviewListResultSetExtractor;
        this.phoneDao = phoneDao;
    }

    private static final String SELECT_ORDERS = """
            SELECT * FROM orders o
            LEFT JOIN order_items oi ON o.orderId = oi.orderId
            """;
    private static final String SELECT_ORDER = SELECT_ORDERS + " WHERE o.orderId = ?";
    private static final String SELECT_ORDER_BY_SECURE_ID = SELECT_ORDERS + " WHERE o.secureId = ?";
    private final String INSERT_ORDER = """
            INSERT INTO orders (subTotal, deliveryPrice, totalPrice, firstName, lastName, deliveryAddress, 
            contactPhoneNo, secureId, additionalInfo, status, dateTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String INSERT_ITEMS = "INSERT INTO order_items (phoneId, orderId, quantity) VALUES ";
    private static final String SELECT_ORDERS_FOR_OVERVIEW = """
            SELECT orderId, firstName, lastName, contactPhoneNo, deliveryAddress, dateTime, totalPrice, status 
            FROM orders
            """;
    private static final String UPDATE_ORDER_STATUS = "UPDATE orders SET status = ? WHERE orderId = ?";

    @Override
    public Optional<Order> get(Long id) {
        Optional<Order> order = Optional.ofNullable(jdbcTemplate.query(SELECT_ORDER, orderResultSetExtractor, id));
        setOrderItemsPhones(order);
        return order;
    }

    @Override
    public Optional<Order> get(UUID secureId) {
        Optional<Order> order = Optional.ofNullable(jdbcTemplate.query(SELECT_ORDER_BY_SECURE_ID,
                orderResultSetExtractor, secureId));
        setOrderItemsPhones(order);
        return order;
    }

    @Override
    public List<OrderOverview> getOrders() {
        return jdbcTemplate.query(SELECT_ORDERS_FOR_OVERVIEW, orderOverviewListResultSetExtractor);
    }

    private void setOrderItemsPhones(Optional<Order> order) {
        order.ifPresent(o -> o.getOrderItems().forEach(orderItem -> phoneDao.get(orderItem.getPhone().getId())
                .ifPresent(orderItem::setPhone)));
    }

    @Override
    @Transactional
    public void save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ORDER);
            setArguments(order, ps);
            return ps;
        }, keyHolder);

        order.setId(keyHolder.getKey().longValue());
        saveItems(order.getOrderItems());
    }

    @Override
    public void setOrderStatus(Long orderId, OrderStatus orderStatus) {
        jdbcTemplate.update(UPDATE_ORDER_STATUS, orderStatus.toString(), orderId);
    }

    private void saveItems(List<OrderItem> orderItems) {
        jdbcTemplate.update(INSERT_ITEMS + getOrderItemsValuesString(orderItems));
    }

    private String getOrderItemsValuesString(List<OrderItem> orderItems) {
        StringBuilder stringBuilder = new StringBuilder();
        String endOfValue = "), ";
        String comma = ", ";

        orderItems.forEach(orderItem -> stringBuilder.append("(")
                .append(orderItem.getPhone().getId())
                .append(comma)
                .append(orderItem.getOrder().getId())
                .append(comma)
                .append(orderItem.getQuantity())
                .append(endOfValue)
        );
        stringBuilder.delete(stringBuilder.lastIndexOf(comma), stringBuilder.length());
        return stringBuilder.toString();
    }

    private void setArguments(Order order, PreparedStatement ps) throws SQLException {
        order.setSecureId(UUID.randomUUID());
        order.setDate(new Date());

        if (order.getSubtotal() == null) {
            ps.setString(1, null);
        } else {
            ps.setBigDecimal(1, order.getSubtotal());
        }

        if (order.getDeliveryPrice() == null) {
            ps.setString(2, null);
        } else {
            ps.setBigDecimal(2, order.getDeliveryPrice());
        }

        if (order.getTotalPrice() == null) {
            ps.setString(3, null);
        } else {
            ps.setBigDecimal(3, order.getTotalPrice());
        }

        ps.setString(4, order.getFirstName());
        ps.setString(5, order.getLastName());
        ps.setString(6, order.getDeliveryAddress());
        ps.setString(7, order.getContactPhoneNo());
        ps.setString(8, order.getSecureId().toString());
        ps.setString(9, order.getAdditionalInfo());
        ps.setString(10, order.getStatus().toString());
        ps.setTimestamp(11, new Timestamp(order.getDate().getTime()));
    }
}
