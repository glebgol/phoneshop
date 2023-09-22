package com.es.phoneshop.web.validation;

import com.es.core.model.phone.Stock;
import com.es.core.model.phone.dao.StockDao;
import com.es.phoneshop.web.dto.CartItemDTO;
import com.es.phoneshop.web.dto.CartItemListDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CartItemListDTOValidator implements Validator {
    private final StockDao stockDao;

    public CartItemListDTOValidator(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CartItemListDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CartItemListDTO cartItemListDTO = (CartItemListDTO) o;

        for (int i = 0; i < cartItemListDTO.getItems().size(); i++) {
            CartItemDTO item = cartItemListDTO.getItems().get(i);
            Long id = item.getPhoneId();
            Long requestedQuantity = item.getQuantity();

            if (requestedQuantity < 1) {
                errors.rejectValue("items['" + i + "'].phoneId","Should be positive integer");
            } else {
                Stock stock = stockDao.getByPhoneId(id).get();
                if (requestedQuantity > stock.getStock() - stock.getReserved()) {
                    errors.rejectValue("items['" + i + "'].phoneId","Out of stock");
                }
            }
        }
    }
}
