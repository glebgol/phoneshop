package com.es.phoneshop.web.controller;

import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.phoneshop.web.dto.CartItemAddingResponse;
import com.es.phoneshop.web.dto.CartItemDTO;
import com.es.phoneshop.web.validation.QuantityValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    private final CartService cartService;
    private final QuantityValidator quantityValidator;

    private static final String SUCCESS_ADDING_TO_CART = "success adding to cart!";
    private static final String INVALID_QUANTITY = "Invalid quantity";

    public AjaxCartController(CartService cartService, QuantityValidator quantityValidator) {
        this.cartService = cartService;
        this.quantityValidator = quantityValidator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(quantityValidator);
    }

    @PostMapping
    public ResponseEntity<CartItemAddingResponse> addPhone(@Valid @RequestBody CartItemDTO cartItemDTO,
                                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new CartItemAddingResponse(INVALID_QUANTITY), HttpStatus.BAD_REQUEST);
        }

        cartService.addPhone(cartItemDTO.getPhoneId().longValue(), cartItemDTO.getQuantity().longValue());

        Cart cart = cartService.getCart();

        return new ResponseEntity<>(new CartItemAddingResponse(cart.getTotalQuantity(), cart.getTotalCost(),
                SUCCESS_ADDING_TO_CART), HttpStatus.OK);
    }
}
