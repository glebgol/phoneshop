package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.dao.PhoneDao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
public class HttpSessionCartService implements CartService {
    private final Cart cart;
    private final PhoneDao phoneDao;

    public HttpSessionCartService(Cart cart, PhoneDao phoneDao) {
        this.cart = cart;
        this.phoneDao = phoneDao;
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        Phone phone = phoneDao.get(phoneId).get();
        Optional<CartItem> cartItemOptional = findCartItemByPhone(phone);

        if (phone.getPrice() == null) {
            phone.setPrice(new BigDecimal(0));
        }
        if (cartItemOptional.isPresent()) {
            updateCartItem(quantity, cartItemOptional);
        } else {
            addNewCartItem(quantity, phone);
        }

        recalculateCart(cart);
    }

    @Override
    public void update(Map<Long, Long> items) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void remove(Long phoneId) {
        throw new UnsupportedOperationException("TODO");
    }

    private void recalculateCart(Cart cart) {
        cart.setTotalQuantity(cart.getItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum());

        cart.setTotalCost(cart.getItems().stream()
                .map(item -> item.getPhone().getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    private Optional<CartItem> findCartItemByPhone(Phone phone) {
        return cart.getItems().stream()
                .filter(cartItem -> cartItem.getPhone().equals(phone))
                .findAny();
    }

    private void addNewCartItem(Long quantity, Phone phone) {
        cart.getItems().add(new CartItem(phone, quantity.intValue()));
    }

    private void updateCartItem(Long quantity, Optional<CartItem> cartItemOptional) {
        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(cartItem.getQuantity() + quantity.intValue());
    }
}
