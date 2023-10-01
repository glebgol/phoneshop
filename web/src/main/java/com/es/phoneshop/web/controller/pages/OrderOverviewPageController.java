package com.es.phoneshop.web.controller.pages;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(value = "/orderOverview")
public class OrderOverviewPageController {
    private final OrderDao orderDao;

    public OrderOverviewPageController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @GetMapping("/{secureId}")
    public String getOrder(@PathVariable UUID secureId, Model model) {
        Optional<Order> order = orderDao.get(secureId);
        if (order.isEmpty()) {
            return "redirect:/productList";
        }

        model.addAttribute("order", order.get());
        return "orderOverview";
    }
}
