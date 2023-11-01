package com.es.phoneshop.web.validation;

import com.es.phoneshop.web.dto.QuickAddDto;
import com.es.phoneshop.web.dto.QuickAddRowDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class QuickAddDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(QuickAddDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        QuickAddDto quickAddDto = (QuickAddDto) o;

        for (int i = 0; i < quickAddDto.getItems().size(); i++) {
            QuickAddRowDto quickAddRowDto = quickAddDto.getItems().get(i);

            if (quickAddRowDto.getQuantity() != null && isBlank(quickAddRowDto.getModel())) {
                errors.rejectValue("items[" + i + "].model", "items[" + i + "].model", "Model should be not blank");
            }

            if (quickAddRowDto.getQuantity() != null && quickAddRowDto.getQuantity() < 1) {
                errors.rejectValue("items[" + i + "].quantity", "items[" + i + "].quantity", "Quantity should be more than 0");
            }
        }
    }
}
