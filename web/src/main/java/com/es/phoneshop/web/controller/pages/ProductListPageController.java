package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.model.phone.SortField;
import com.es.core.model.phone.SortOrder;
import com.es.core.model.phone.dao.PhoneDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {
    private final PhoneDao phoneDao;
    private static final Integer PAGE_LIMIT = 10;
    private final CartService cartService;

    public ProductListPageController(PhoneDao phoneDao, CartService cartService) {
        this.phoneDao = phoneDao;
        this.cartService = cartService;
    }

    @GetMapping
    public String showProductList(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "", required = false) String search,
                                  @RequestParam(required = false) SortField sort,
                                  @RequestParam(required = false) SortOrder order, Model model) {
        int offset = PAGE_LIMIT * (page - 1);

        model.addAttribute("phones", phoneDao.findAllInStock(search, sort, order, offset, PAGE_LIMIT));
        model.addAttribute("pagesCount", getPagesCount(search));
        model.addAttribute("currentPage", page);

        return "productList";
    }

    @ModelAttribute
    public void addCartAttribute(Model model) {
        model.addAttribute("cart", cartService.getCart());
    }

    private int getPagesCount(String search) {
        return (int) Math.ceil(phoneDao.countPhones(search) / (PAGE_LIMIT * 1.0));
    }
}
