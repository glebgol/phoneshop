package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.order.OutOfStockException;
import com.es.phoneshop.web.dto.CartItemDTO;
import com.es.phoneshop.web.dto.CartDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    private final CartService cartService;
    private final Validator validator;

    private static final String SUCCESS_MESSAGE = "Successful cart updating";

    public CartPageController(CartService cartService, @Qualifier("cartDTOValidator") Validator validator) {
        this.cartService = cartService;
        this.validator = validator;
    }

    @ModelAttribute(value = "cartDTO")
    public void addCartAttribute(Model model) {
        model.addAttribute("cartDTO", getCartDTO());
    }

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("cart", cartService.getCart());

        return "cart";
    }

    @PostMapping
    public String updateCart(@ModelAttribute("cartDTO") CartDTO cartDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws OutOfStockException {
        validator.validate(cartDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("cartDTO", cartDTO);
            redirectAttributes.addFlashAttribute("errors", getErrors(bindingResult));
            return "redirect:/cart";
        }

        cartService.update(mapCartDTOToMap(cartDTO));

        redirectAttributes.addFlashAttribute("successMessage", SUCCESS_MESSAGE);
        return "redirect:/cart";
    }

    @PostMapping("/delete/{phoneId}")
    public String deleteCartItem(@PathVariable Long phoneId) {
        cartService.remove(phoneId);

        return "redirect:/cart";
    }

    private static Map<Long, Long> mapCartDTOToMap(CartDTO cartDTO) {
        return new HashMap<>(cartDTO.getItems().stream()
                .collect(Collectors.toMap(CartItemDTO::getPhoneId, CartItemDTO::getQuantity)));
    }

    private CartDTO getCartDTO() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setItems(cartService.getCart().getItems().stream()
                .map(cartItem -> new CartItemDTO(cartItem.getPhone().getId(), (long) cartItem.getQuantity()))
                .collect(Collectors.toList()));
        return cartDTO;
    }

    private Map<String, String> getErrors(BindingResult bindingResult) {
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : fieldErrorList) {
            if (fieldError.getDefaultMessage().contains("java.lang.NumberFormatException")) {
                errors.put(fieldError.getField(), "Quantity should be an integer");
            } else {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
        System.out.println(errors);
        return errors;
    }
}
