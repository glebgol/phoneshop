package com.es.core.model.phone.comparators;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortOrder;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class PhonePriceComparatorTest {
    private PhonePriceComparator phonePriceComparator;

    private static final BigDecimal LARGER_PRICE = new BigDecimal(7);
    private static final BigDecimal LOWER_PRICE = new BigDecimal(5);

    private static final Phone PHONE1 = Phone.builder().price(LARGER_PRICE).build();
    private static final Phone PHONE2 = Phone.builder().price(LOWER_PRICE).build();

    @Test
    public void comparePhonesAsc() {
        phonePriceComparator = new PhonePriceComparator(SortOrder.ASC);

        int comparison = phonePriceComparator.compare(PHONE1, PHONE2);

        assertTrue(comparison > 0);
    }

    @Test
    public void comparePhonesDesc() {
        phonePriceComparator = new PhonePriceComparator(SortOrder.DESC);

        int comparison = phonePriceComparator.compare(PHONE1, PHONE2);

        assertTrue(comparison < 0);
    }
}
