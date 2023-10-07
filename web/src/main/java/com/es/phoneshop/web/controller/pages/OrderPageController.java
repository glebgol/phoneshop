package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.order.OrderService;
import com.es.core.order.OutOfStockException;
import com.es.phoneshop.web.dto.OrderForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {
    private final OrderService orderService;
    private final Cart cart;
    private static final String ITEMS_HAVE_BEEN_REMOVED = "Cannot order items! Out of stock items have been removed!";

    public OrderPageController(OrderService orderService, Cart cart) {
        this.orderService = orderService;
        this.cart = cart;
    }

    @ModelAttribute(name = "order")
    public Order createOrder() {
        return orderService.createOrder(cart);
    }

    @ModelAttribute(name = "orderForm")
    public OrderForm addOrderForm() {
        return new OrderForm();
    }

    @GetMapping
    public String getOrder() {
        return "order";
    }

    @PostMapping
    public String placeOrder(@ModelAttribute Order order, @ModelAttribute @Valid OrderForm orderForm,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("orderForm", orderForm);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", getErrors(bindingResult));
            return "redirect:/order";
        }

        setFormData(orderForm, order);

        try {
            orderService.placeOrder(order);
        } catch (OutOfStockException e) {
            redirectAttributes.addFlashAttribute("message", ITEMS_HAVE_BEEN_REMOVED);
            return "redirect:/order";
        }

        return "redirect:/orderOverview/" + order.getSecureId();
    }

    private void setFormData(OrderForm orderForm, Order order) {
        order.setFirstName(orderForm.getFirstName());
        order.setLastName(orderForm.getLastName());
        order.setDeliveryAddress(orderForm.getDeliveryAddress());
        order.setContactPhoneNo(orderForm.getContactPhoneNo());
        order.setAdditionalInfo(orderForm.getAdditionalInfo());
    }

    private Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(),
                fieldError.getDefaultMessage()));
        return errors;
    }
}
