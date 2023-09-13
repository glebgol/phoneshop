package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;

import java.util.Comparator;

public abstract class PhoneComparator implements Comparator<Phone> {
    protected final SortOrder sortOrder;

    protected PhoneComparator(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
}
