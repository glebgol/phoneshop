package com.es.phoneshop.web.controller;

import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.order.OutOfStockException;
import com.es.phoneshop.web.dto.CartItemAddingResponse;
import com.es.phoneshop.web.dto.CartItemDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    private final CartService cartService;
    private final Validator validator;

    private static final String SUCCESS_ADDING_TO_CART = "success adding to cart!";
    private static final String INVALID_QUANTITY = "Invalid quantity";
    private static final String OUT_OF_STOCK = "Quantity is out of stock!";

    public AjaxCartController(CartService cartService, @Qualifier("quantityValidator") Validator validator) {
        this.cartService = cartService;
        this.validator = validator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @PostMapping
    public ResponseEntity<CartItemAddingResponse> addPhone(@Valid @RequestBody CartItemDTO cartItemDTO,
                                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new CartItemAddingResponse(INVALID_QUANTITY), HttpStatus.BAD_REQUEST);
        }

        try {
            cartService.addPhone(cartItemDTO.getPhoneId(), cartItemDTO.getQuantity());
        } catch (OutOfStockException e) {
            return new ResponseEntity<>(new CartItemAddingResponse(OUT_OF_STOCK), HttpStatus.BAD_REQUEST);
        }

        Cart cart = cartService.getCart();

        return new ResponseEntity<>(new CartItemAddingResponse(cart.getTotalQuantity(), cart.getTotalCost(),
                SUCCESS_ADDING_TO_CART), HttpStatus.OK);
    }
}
