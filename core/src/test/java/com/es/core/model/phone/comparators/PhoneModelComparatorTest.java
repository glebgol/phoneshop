package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PhoneModelComparatorTest {
    private PhoneModelComparator phoneModelComparator;

    private static final String ALPHABETICALLY_HIGHER_MODEL = "S11";
    private static final String ALPHABETICALLY_LOWER_MODEL = "S12";

    private static final Phone PHONE1 = Phone.builder().model(ALPHABETICALLY_HIGHER_MODEL).build();
    private static final Phone PHONE2 = Phone.builder().model(ALPHABETICALLY_LOWER_MODEL).build();

    @Test
    public void comparePhonesAsc() {
        phoneModelComparator = new PhoneModelComparator(SortOrder.ASC);

        int comparison = phoneModelComparator.compare(PHONE1, PHONE2);

        assertTrue(comparison < 0);
    }

    @Test
    public void comparePhonesDesc() {
        phoneModelComparator = new PhoneModelComparator(SortOrder.DESC);

        int comparison = phoneModelComparator.compare(PHONE1, PHONE2);

        assertTrue(comparison > 0);
    }
}
