package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;
import org.apache.commons.lang3.ObjectUtils;

public class PhonePriceComparator extends PhoneComparator {
    protected PhonePriceComparator(SortOrder sortOrder) {
        super(sortOrder);
    }

    @Override
    public int compare(Phone p1, Phone p2) {
        int comparison = ObjectUtils.compare(p1.getPrice(), p2.getPrice());
        return sortOrder == SortOrder.ASC ? comparison : -comparison;
    }
}
