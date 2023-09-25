package com.es.phoneshop.web.validation;

import com.es.core.model.phone.Stock;
import com.es.core.model.phone.dao.StockDao;
import com.es.phoneshop.web.dto.CartItemDTO;
import com.es.phoneshop.web.dto.CartDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CartDTOValidator implements Validator {
    private final StockDao stockDao;

    public CartDTOValidator(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CartDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CartDTO cartDTO = (CartDTO) o;

        for (int i = 0; i < cartDTO.getItems().size(); i++) {
            CartItemDTO item = cartDTO.getItems().get(i);
            Long id = item.getPhoneId();
            Long updateQuantity = item.getQuantity();

            ValidationUtils.rejectIfEmpty(errors, "items[" + i + "].quantity", "empty value");
            ValidationUtils.rejectIfEmpty(errors, "items[" + i + "].phoneId", "empty value");

            if (updateQuantity == null) {
                errors.rejectValue("items['" + i + "'].phoneId", "Invalid quantity", "Invalid quantity");
            } else if (updateQuantity < 1) {
                errors.rejectValue("items['" + i + "'].quantity", "","Should be positive integer");
            } else {
                Stock stock = stockDao.getByPhoneId(id).get();
                if (updateQuantity > stock.getStock()) {
                    errors.rejectValue("items['" + i + "'].quantity","Out of stock", "Out of stock");
                }
            }
        }
    }
}
