package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;

public class PhonePriceComparator extends PhoneComparator {
    protected PhonePriceComparator(SortOrder sortOrder) {
        super(sortOrder);
    }

    @Override
    public int compare(Phone p1, Phone p2) {
        int comparison = p1.getPrice().compareTo(p2.getPrice());
        return sortOrder == SortOrder.ASC ? comparison : -comparison;
    }
}
