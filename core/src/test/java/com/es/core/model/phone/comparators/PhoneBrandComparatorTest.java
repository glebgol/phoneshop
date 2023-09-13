package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PhoneBrandComparatorTest {
    private PhoneBrandComparator phoneBrandComparator;

    private static final String ALPHABETICALLY_HIGHER_BRAND = "Apple";
    private static final String ALPHABETICALLY_LOWER_BRAND = "Samsung";

    private static final Phone PHONE1 = Phone.builder().brand(ALPHABETICALLY_HIGHER_BRAND).build();
    private static final Phone PHONE2 = Phone.builder().brand(ALPHABETICALLY_LOWER_BRAND).build();

    @Test
    public void comparePhonesAsc() {
        phoneBrandComparator = new PhoneBrandComparator(SortOrder.ASC);

        int comparison = phoneBrandComparator.compare(PHONE1, PHONE2);

        assertTrue(comparison < 0);
    }

    @Test
    public void comparePhonesDesc() {
        phoneBrandComparator = new PhoneBrandComparator(SortOrder.DESC);

        int comparison = phoneBrandComparator.compare(PHONE1, PHONE2);

        assertTrue(comparison > 0);
    }
}
