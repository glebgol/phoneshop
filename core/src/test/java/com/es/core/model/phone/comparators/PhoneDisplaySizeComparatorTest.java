package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class PhoneDisplaySizeComparatorTest {
    private PhoneDisplaySizeComparator phoneDisplaySizeComparator;

    private static final BigDecimal LARGER_SIZE = new BigDecimal(7);
    private static final BigDecimal LOWER_SIZE = new BigDecimal(5);

    private static final Phone PHONE1 = Phone.builder().displaySizeInches(LARGER_SIZE).build();
    private static final Phone PHONE2 = Phone.builder().displaySizeInches(LOWER_SIZE).build();

    @Test
    public void comparePhonesAsc() {
        phoneDisplaySizeComparator = new PhoneDisplaySizeComparator(SortOrder.ASC);

        int comparison = phoneDisplaySizeComparator.compare(PHONE1, PHONE2);

        assertTrue(comparison > 0);
    }

    @Test
    public void comparePhonesDesc() {
        phoneDisplaySizeComparator = new PhoneDisplaySizeComparator(SortOrder.DESC);

        int comparison = phoneDisplaySizeComparator.compare(PHONE1, PHONE2);

        assertTrue(comparison < 0);
    }
}
