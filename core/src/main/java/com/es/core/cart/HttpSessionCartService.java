package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.Stock;
import com.es.core.model.phone.dao.PhoneDao;
import com.es.core.model.phone.dao.StockDao;
import com.es.core.order.OutOfStockException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
public class HttpSessionCartService implements CartService {
    private final Cart cart;
    private final PhoneDao phoneDao;
    private final StockDao stockDao;

    public HttpSessionCartService(Cart cart, PhoneDao phoneDao, StockDao stockDao) {
        this.cart = cart;
        this.phoneDao = phoneDao;
        this.stockDao = stockDao;
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) throws OutOfStockException {
        Optional<CartItem> cartItem = findCartItemByPhone(phoneId);

        if (cartItem.isPresent()) {
            addCartItemQuantity(quantity, cartItem.get());
        } else {
            createCartItem(quantity, phoneId);
        }

        recalculateCart();
    }

    @Override
    public void update(Map<Long, Long> items) throws OutOfStockException {
        for (var item: items.entrySet()) {
            Long phoneId = item.getKey();
            Long quantity = item.getValue();
            Stock stock = stockDao.getByPhoneId(phoneId);

            if (stock.getStock() < quantity) {
                throw new OutOfStockException();
            }

            cart.getItems().forEach(cartItem -> {
                if (cartItem.getPhone().getId().equals(phoneId)) {
                    cartItem.setQuantity(quantity.intValue());
                }
            });
        }
        recalculateCart();
    }

    @Override
    public void remove(Long phoneId) {
        cart.getItems().removeIf(p -> p.getPhone().getId().equals(phoneId));
        recalculateCart();
    }

    @Override
    public void clearCart() {
        cart.getItems().clear();
        cart.setTotalQuantity(0);
        cart.setTotalCost(new BigDecimal(0));
    }

    @Override
    public void removeOutOfStockItems() throws OutOfStockException {
        int totalQuantityBeforeRemoving = cart.getTotalQuantity();

        cart.getItems().removeIf(cartItem ->
                cartItem.getQuantity() > stockDao.getByPhoneId(cartItem.getPhone().getId()).getStock());
        recalculateCart();

        int totalQuantityAfterRemoving = cart.getTotalQuantity();

        if (totalQuantityBeforeRemoving > totalQuantityAfterRemoving) {
            throw new OutOfStockException();
        }
    }

    private void recalculateCart() {
        cart.setTotalQuantity(cart.getItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum());

        cart.setTotalCost(cart.getItems().stream()
                .map(item -> item.getPhone().getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    private Optional<CartItem> findCartItemByPhone(Long phoneId) {
        return cart.getItems().stream()
                .filter(cartItem -> cartItem.getPhone().getId().equals(phoneId))
                .findAny();
    }

    private void createCartItem(Long quantity, Long phoneId) throws OutOfStockException {
        Phone phone = phoneDao.get(phoneId).get();
        checkCreatingPossibility(quantity, phone);
        cart.getItems().add(new CartItem(phone, quantity.intValue()));
    }

    private void addCartItemQuantity(Long quantity, CartItem cartItem) throws OutOfStockException {
        checkAddingPossibility(cartItem, quantity);
        cartItem.setQuantity(cartItem.getQuantity() + quantity.intValue());
    }

    private void checkCreatingPossibility(Long quantity, Phone phone) throws OutOfStockException {
        Stock stock = stockDao.getByPhoneId(phone.getId());

        if (stock.getStock() < quantity) {
            throw new OutOfStockException();
        }
    }

    private void checkAddingPossibility(CartItem cartItem, Long quantity) throws OutOfStockException {
        Stock stock = stockDao.getByPhoneId(cartItem.getPhone().getId());

        if (stock.getStock() < quantity + cartItem.getQuantity()) {
            throw new OutOfStockException();
        }
    }
}
