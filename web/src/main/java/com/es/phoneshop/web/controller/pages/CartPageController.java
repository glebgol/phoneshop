package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartItem;
import com.es.core.cart.CartService;
import com.es.core.order.OutOfStockException;
import com.es.phoneshop.web.dto.CartItemDTO;
import com.es.phoneshop.web.dto.CartItemListDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    private final CartService cartService;

    public CartPageController(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute
    public void addCartAttribute(Model model) {
        model.addAttribute("cart", cartService.getCart());
    }

    @GetMapping
    public String getCart(@ModelAttribute("cartItemListDTO") CartItemListDTO cartItemListDTO) {
        cartItemListDTO.setItems(map(cartService.getCart().getItems()));

        return "cart";
    }

    private List<CartItemDTO> map(List<CartItem> items) {
        return items.stream().map(item -> new CartItemDTO(item.getPhone().getId(), (long) item.getQuantity()))
                .collect(Collectors.toList());
    }

    @PutMapping
    public String updateCart(@ModelAttribute("cartItemListDTO") CartItemListDTO cartItemListDTO) throws OutOfStockException {
        Map<Long, Long> items = new HashMap<>(cartItemListDTO.getItems().stream()
                .collect(Collectors.toMap(CartItemDTO::getPhoneId, CartItemDTO::getQuantity)));

        cartService.update(items);

        return "cart";
    }

    @PostMapping("/{phoneId}")
    public String deleteCartItem(@PathVariable Long phoneId) {
        cartService.remove(phoneId);

        return "redirect:/cart";
    }
}
