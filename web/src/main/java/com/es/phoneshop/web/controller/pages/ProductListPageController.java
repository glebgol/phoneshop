package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.model.phone.SortField;
import com.es.core.model.phone.SortOrder;
import com.es.core.model.phone.dao.PhoneDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {
    private final PhoneDao phoneDao;
    private final Cart cart;
    private static final Integer PAGE_LIMIT = 10;
    private final CartService cartService;
    public static final String SUCCESS_ADDING_TO_CART = "success adding to cart!";


    public ProductListPageController(PhoneDao phoneDao, Cart cart, CartService cartService) {
        this.phoneDao = phoneDao;
        this.cart = cart;
        this.cartService = cartService;
    }

    @GetMapping
    public String showProductList(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(required = false) String search,
                                  @RequestParam(required = false) SortField sort,
                                  @RequestParam(required = false) SortOrder order, Model model) {
        int offset = PAGE_LIMIT * (page - 1);

        if (sort == null || order == null) {
            model.addAttribute("phones", phoneDao.findAllInStock(search, offset, PAGE_LIMIT));
        } else {
            model.addAttribute("phones", phoneDao.findAllInStock(search, sort, order, offset, PAGE_LIMIT));
        }

        model.addAttribute("pagesCount", getPagesCount(search));
        model.addAttribute("currentPage", page);
        model.addAttribute("cart", cart);

        return "productList";
    }

    private int getPagesCount(String search) {
        return (int) Math.ceil(phoneDao.countPhones(search) / (PAGE_LIMIT * 1.0));
    }
}
