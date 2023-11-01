package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.dao.PhoneDao;
import com.es.core.order.OutOfStockException;
import com.es.phoneshop.web.dto.QuickAddDto;
import com.es.phoneshop.web.dto.QuickAddRowDto;
import com.es.phoneshop.web.validation.QuickAddDtoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Controller
@RequestMapping("/quickAdd")
public class QuickAddPageController {

    private final PhoneDao phoneDao;
    private final CartService cartService;
    private final QuickAddDtoValidator quickAddDtoValidator;

    private static final Integer QUICK_ADD_ITEMS_COUNT = 5;
    private static final String QUICK_ADD_PAGE_REDIRECT = "redirect:/quickAdd";
    private static final String ERROR = "There was errors";

    public QuickAddPageController(PhoneDao phoneDao, CartService cartService, QuickAddDtoValidator quickAddDtoValidator) {
        this.phoneDao = phoneDao;
        this.cartService = cartService;
        this.quickAddDtoValidator = quickAddDtoValidator;
    }

    @ModelAttribute
    public QuickAddDto quickAddDto() {
        return new QuickAddDto(QUICK_ADD_ITEMS_COUNT);
    }

    @ModelAttribute("quickAddItemsCount")
    public Integer quickAddItemsCount() {
        return QUICK_ADD_ITEMS_COUNT;
    }

    @GetMapping
    public String showQuickAddPage() {
        return "quickAdd";
    }

    @ModelAttribute
    public void addCartAttribute(Model model) {
        model.addAttribute("cart", cartService.getCart());
    }

    @PostMapping
    public String makeQuickAdd(@ModelAttribute("quickAddDto") QuickAddDto quickAddDto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("quickAddDto", quickAddDto);
        quickAddDtoValidator.validate(quickAddDto, bindingResult);

        Map<String, String> errors = new HashMap<>();
        List<String> successfulAdditions = new ArrayList<>();

        for (int i = 0; i < quickAddDto.getItems().size(); i++) {
            QuickAddRowDto quickAddRowDto = quickAddDto.getItems().get(i);

            Optional<Phone> optionalPhone = phoneDao.get(quickAddRowDto.getModel());

            if (optionalPhone.isPresent()) {
                Phone phone = optionalPhone.get();
                addToCart(phone, errors, successfulAdditions, quickAddRowDto, i);
            } else if (!isBlank(quickAddRowDto.getModel())) {
                addPhoneNotFoundError(errors, i);
            }
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", ERROR);
            errors.putAll(getErrors(bindingResult));
        }

        redirectAttributes.addFlashAttribute("success", successfulAdditions);
        redirectAttributes.addFlashAttribute("errors", errors);
        return QUICK_ADD_PAGE_REDIRECT;
    }

    private void addToCart(Phone phone, Map<String, String> errors, List<String> successfulAdditions,
                           QuickAddRowDto quickAddRowDto, int index) {
        if (isValid(quickAddRowDto)) {
            try {
                cartService.addPhone(phone.getId(), quickAddRowDto.getQuantity());
                successfulAdditions.add(phone.getModel());
            } catch (OutOfStockException e) {
                addOutOfStockError(errors, index);
            }
        }
    }

    private void addPhoneNotFoundError(Map<String, String> errors, int index) {
        errors.put("items[" + index + "].model", "Not found");
    }

    private void addOutOfStockError(Map<String, String> errors, int index) {
        errors.put("items[" + index + "].quantity", "Out of stock");
    }

    private Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(objectError -> errors.put(objectError.getCode(),
                objectError.getDefaultMessage()));
        return errors;
    }

    private boolean isValid(QuickAddRowDto quickAddRowDto) {
        return quickAddRowDto.getQuantity() != null && quickAddRowDto.getQuantity() > 0L
                && !isBlank(quickAddRowDto.getModel());
    }
}
