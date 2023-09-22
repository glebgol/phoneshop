package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.dao.PhoneDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {
    private final PhoneDao phoneDao;
    private final CartService cartService;

    public ProductDetailsPageController(PhoneDao phoneDao, CartService cartService) {
        this.phoneDao = phoneDao;
        this.cartService = cartService;
    }

    @GetMapping("/{phoneId}")
    public String showPhoneById(@PathVariable Long phoneId, Model model) {
        Optional<Phone> phone = phoneDao.get(phoneId);

        if (phone.isEmpty()) {
            return "productNotFound";
        }

        model.addAttribute("phone", phone.get());

        return "productDetails";
    }

    @ModelAttribute
    public void addCartAttribute(Model model) {
        model.addAttribute("cart", cartService.getCart());
    }
}
