package com.es.phoneshop.web.controller.pages.admin;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderDao;
import com.es.phoneshop.web.dto.OrderUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrdersPageController {
    private final OrderDao orderDao;

    public OrdersPageController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @GetMapping
    public String showOrders(Model model) {
        model.addAttribute("orders", orderDao.getOrders());

        return "admin/orders";
    }

    @GetMapping("/{orderId}")
    public String showOrders(@PathVariable Long orderId, Model model, RedirectAttributes redirectAttributes) {
        Optional<Order> order = orderDao.get(orderId);

        if (order.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Order " + orderId + " not found");
            return "redirect:/admin/orders";
        }

        model.addAttribute("order", order.get());
        return "admin/orderOverview";
    }

    @ResponseBody
    @PatchMapping("/{orderId}")
    public ResponseEntity<?> partialOrderUpdate(@RequestBody OrderUpdateDto orderUpdateDto,
                                                @PathVariable Long orderId) {
        Optional<Order> order = orderDao.get(orderId);

        if (order.isEmpty()) {
            return ResponseEntity.status(404).body("Order " + orderId + " not found!");
        }

        orderDao.setOrderStatus(orderId, orderUpdateDto.getOrderStatus());

        return ResponseEntity.ok("Order status: " + orderUpdateDto.getOrderStatus());
    }
}
