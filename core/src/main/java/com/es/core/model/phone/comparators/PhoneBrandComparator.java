package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;

public class PhoneBrandComparator extends PhoneComparator {
    protected PhoneBrandComparator(SortOrder sortOrder) {
        super(sortOrder);
    }

    @Override
    public int compare(Phone p1, Phone p2) {
        int comparison = p1.getBrand().compareTo(p2.getBrand());
        return sortOrder == SortOrder.ASC ? comparison : -comparison;
    }
}
