package com.es.core.model.order;

import com.es.core.model.phone.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:context/applicationContext-core-test.xml")
public class JdbcOrderDaoIntTest {
    @Autowired
    OrderDao orderDao;

    private static final Order ORDER = new Order();
    private static List<OrderItem> ORDER_ITEMS = new ArrayList<>();
    private static final OrderItem ORDER_ITEM = new OrderItem();
    private static final OrderItem ORDER_ITEM_2 = new OrderItem();
    private static final Long QUANTITY = 100L;
    private static final Phone PHONE = Phone.builder().id(1003L).build();
    private static final Phone PHONE_2 = Phone.builder().id(1005L).build();
    private static final Long ORDER_ID = 1L;

    @Before
    public void setValues() {
        ORDER_ITEM.setQuantity(QUANTITY);
        ORDER_ITEM.setOrder(ORDER);
        ORDER_ITEM.setPhone(PHONE);

        ORDER_ITEM_2.setQuantity(QUANTITY);
        ORDER_ITEM_2.setOrder(ORDER);
        ORDER_ITEM_2.setPhone(PHONE_2);

        ORDER_ITEMS = Stream.of(ORDER_ITEM, ORDER_ITEM_2).collect(Collectors.toList());
        ORDER.setOrderItems(ORDER_ITEMS);
        ORDER.setStatus(OrderStatus.NEW);
    }

    @Test
    public void saveAndGetOrder() {
        orderDao.save(ORDER);
        Optional<Order> order = orderDao.get(ORDER_ID);

        assertEquals(ORDER_ID, order.get().getId());
        assertEquals(ORDER.getOrderItems().size(), order.get().getOrderItems().size());
    }
}
