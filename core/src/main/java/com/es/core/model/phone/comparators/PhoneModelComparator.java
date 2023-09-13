package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;

public class PhoneModelComparator extends PhoneComparator {
    protected PhoneModelComparator(SortOrder sortOrder) {
        super(sortOrder);
    }

    @Override
    public int compare(Phone p1, Phone p2) {
        int comparison = p1.getModel().compareTo(p2.getModel());
        return sortOrder == SortOrder.ASC ? comparison : -comparison;
    }
}
