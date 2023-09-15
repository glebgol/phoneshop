package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;
import org.apache.commons.lang3.ObjectUtils;

public class PhoneDisplaySizeComparator extends PhoneComparator {
    protected PhoneDisplaySizeComparator(SortOrder sortOrder) {
        super(sortOrder);
    }

    @Override
    public int compare(Phone p1, Phone p2) {
        int comparison = ObjectUtils.compare(p1.getDisplaySizeInches(), p2.getDisplaySizeInches());
        return sortOrder == SortOrder.ASC ? comparison : -comparison;
    }
}
