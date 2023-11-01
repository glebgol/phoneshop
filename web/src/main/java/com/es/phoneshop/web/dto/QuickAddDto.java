package com.es.phoneshop.web.dto;

import java.util.ArrayList;
import java.util.List;

public class QuickAddDto {
    private List<QuickAddRowDto> items;

    public QuickAddDto(int rowsCount) {
        items = new ArrayList<>();
        for (int i = 0; i < rowsCount; i++) {
            items.add(new QuickAddRowDto());
        }
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
