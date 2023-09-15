package com.es.phoneshop.web.controller;

import com.es.core.cart.CartService;
import com.es.phoneshop.web.dto.CartItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    private final CartService cartService;
    public static final String SUCCESS_ADDING_TO_CART = "success adding to cart!";

    public AjaxCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addPhone(@RequestParam Long phoneId, @RequestParam Long quantity
                                           /*@RequestBody CartItemDTO, BindingResult bindingResult*/) {
        cartService.addPhone(phoneId, quantity);
        return new ResponseEntity<>(SUCCESS_ADDING_TO_CART, HttpStatus.OK);
    }
}
