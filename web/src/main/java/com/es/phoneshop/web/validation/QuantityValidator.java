package com.es.phoneshop.web.validation;

import com.es.phoneshop.web.dto.CartItemDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class QuantityValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CartItemDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CartItemDTO cartItemDto = (CartItemDTO) o;

        ValidationUtils.rejectIfEmpty(errors, "quantity", "empty value");

        if (cartItemDto.getQuantity() <= 0) {
            errors.rejectValue("quantity", "negative value");
        }
    }
}
