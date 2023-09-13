package com.es.phoneshop.web.controller.pages;

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

    public ProductListPageController(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @GetMapping
    public String showProductList(@RequestParam(defaultValue = "1") Integer page, Model model) {
        final int limit = 20;
        final int offset = (page - 1) * limit;

        model.addAttribute("phones", phoneDao.findAllWithPositiveStock(offset, limit));
        return "productList";
    }
}
