package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;

public class PhoneDisplaySizeComparator extends PhoneComparator {
    protected PhoneDisplaySizeComparator(SortOrder sortOrder) {
        super(sortOrder);
    }

    @Override
    public int compare(Phone p1, Phone p2) {
        int comparison = p1.getDisplaySizeInches().compareTo(p2.getDisplaySizeInches());
        return sortOrder == SortOrder.ASC ? comparison : -comparison;
    }
}
