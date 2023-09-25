package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.Stock;
import com.es.core.model.phone.dao.PhoneDao;
import com.es.core.model.phone.dao.StockDao;
import com.es.core.order.OutOfStockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HttpSessionCartServiceTest {
    @Mock
    Cart cart;
    @Mock
    PhoneDao phoneDao;
    @Mock
    StockDao stockDao;
    @InjectMocks
    HttpSessionCartService cartService;

    private static final Long PHONE_ID = 1L;
    private static final Long QUANTITY = 3L;
    private static final Long OUT_OF_STOCK_QUANTITY = 300L;
    private static final Phone PHONE = Phone.builder().id(PHONE_ID).price(new BigDecimal(100)).build();
    private static final List<CartItem> EMPTY_CART_ITEM_LIST = new ArrayList<>();
    private static final List<CartItem> CART_ITEM_LIST = List.of(new CartItem(PHONE, QUANTITY.intValue()));
    private static final List<CartItem> CART_ITEM_LIST_FOR_REMOVING = Stream.of(new CartItem(PHONE, QUANTITY.intValue()))
            .collect(Collectors.toList());
    private static final Stock STOCK = new Stock(30);
    private static final Map<Long, Long> UPDATE_ITEMS = Map.of(PHONE_ID, QUANTITY);
    private static final Map<Long, Long> OUT_OF_STOCK_UPDATE_ITEMS = Map.of(PHONE_ID, OUT_OF_STOCK_QUANTITY);

    @Test
    public void addNewPhoneToCart() throws OutOfStockException {
        when(phoneDao.get(PHONE_ID)).thenReturn(Optional.of(PHONE));
        when(cart.getItems()).thenReturn(EMPTY_CART_ITEM_LIST);
        when(stockDao.getByPhoneId(PHONE_ID)).thenReturn(STOCK);
        BigDecimal expectedTotalCost = PHONE.getPrice().multiply(BigDecimal.valueOf(QUANTITY));
        int expectedTotalQuantity = QUANTITY.intValue();

        cartService.addPhone(PHONE_ID, QUANTITY);

        verify(cart).setTotalCost(expectedTotalCost);
        verify(cart).setTotalQuantity(expectedTotalQuantity);
    }

    @Test(expected = OutOfStockException.class)
    public void addNewPhoneToCartThrowsOutOfStockException() throws OutOfStockException {
        when(phoneDao.get(PHONE_ID)).thenReturn(Optional.of(PHONE));
        when(cart.getItems()).thenReturn(EMPTY_CART_ITEM_LIST);
        when(stockDao.getByPhoneId(PHONE_ID)).thenReturn(STOCK);

        cartService.addPhone(PHONE_ID, OUT_OF_STOCK_QUANTITY);
    }

    @Test
    public void addExistingPhoneToCart() throws OutOfStockException {
        when(cart.getItems()).thenReturn(CART_ITEM_LIST);
        when(stockDao.getByPhoneId(PHONE_ID)).thenReturn(STOCK);
        BigDecimal expectedTotalCost = PHONE.getPrice().multiply(BigDecimal.valueOf(QUANTITY * 2));
        int expectedTotalQuantity = QUANTITY.intValue() * 2;

        cartService.addPhone(PHONE_ID, QUANTITY);

        verify(cart).setTotalCost(expectedTotalCost);
        verify(cart).setTotalQuantity(expectedTotalQuantity);
    }

    @Test(expected = OutOfStockException.class)
    public void addExistingPhoneToCartThrowsOutOfStockException() throws OutOfStockException {
        when(cart.getItems()).thenReturn(CART_ITEM_LIST);
        when(stockDao.getByPhoneId(PHONE_ID)).thenReturn(STOCK);

        cartService.addPhone(PHONE_ID, OUT_OF_STOCK_QUANTITY);
    }

    @Test
    public void updateCart() throws OutOfStockException {
        when(stockDao.getByPhoneId(PHONE_ID)).thenReturn(STOCK);
        when(cart.getItems()).thenReturn(CART_ITEM_LIST);
        BigDecimal expectedTotalCost = PHONE.getPrice().multiply(BigDecimal.valueOf(QUANTITY));
        int expectedTotalQuantity = QUANTITY.intValue();

        cartService.update(UPDATE_ITEMS);

        verify(cart).setTotalCost(expectedTotalCost);
        verify(cart).setTotalQuantity(expectedTotalQuantity);
    }

    @Test(expected = OutOfStockException.class)
    public void updateCartThrowsOutOfStockException() throws OutOfStockException {
        when(stockDao.getByPhoneId(PHONE_ID)).thenReturn(STOCK);

        cartService.update(OUT_OF_STOCK_UPDATE_ITEMS);
    }

    @Test
    public void getCart() {
        assertSame(cart, cartService.getCart());
    }

    @Test
    public void removeCartItem() {
        when(cart.getItems()).thenReturn(CART_ITEM_LIST_FOR_REMOVING);
        BigDecimal expectedTotalCost = new BigDecimal(0);
        int expectedTotalQuantity = 0;

        cartService.remove(PHONE_ID);

        verify(cart).setTotalCost(expectedTotalCost);
        verify(cart).setTotalQuantity(expectedTotalQuantity);
    }
}
