package com.es.phoneshop.web.dto;

import java.util.Arrays;
import java.util.List;

public class QuickAddDto {
    private List<QuickAddRowDto> items;

    public QuickAddDto(int rowsCount) {
        items = Arrays.asList(new QuickAddRowDto[rowsCount]);
    }

    public QuickAddDto() {
    }

    public List<QuickAddRowDto> getItems() {
        return items;
    }

    public void setItems(List<QuickAddRowDto> items) {
        this.items = items;
    }
}
