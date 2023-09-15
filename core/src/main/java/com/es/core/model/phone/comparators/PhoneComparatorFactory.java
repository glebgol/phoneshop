package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortField;
import com.es.core.model.phone.SortOrder;

import java.util.Comparator;

public class PhoneComparatorFactory {
    public static Comparator<Phone> createComparator(SortField sortField, SortOrder sortOrder) {
        if (sortField == SortField.BRAND) {
            return new PhoneBrandComparator(sortOrder);
        } else if (sortField == SortField.MODEL) {
            return new PhoneModelComparator(sortOrder);
        } else if (sortField == SortField.PRICE) {
            return new PhonePriceComparator(sortOrder);
        } else if (sortField == SortField.DISPLAY_SIZE) {
            return new PhoneDisplaySizeComparator(sortOrder);
        } else {
            throw new RuntimeException("There are no comparators for " + sortField);
        }
    }
}
