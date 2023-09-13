package com.es.core.model.phone.predicates;

import com.es.core.model.phone.Phone;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SearchPhonePredicateTest {
    private SearchPhonePredicate searchPhonePredicate;
    private static final String SEARCH_BY_BRAND = "Samsung";
    private static final String SEARCH_BY_MODEL = "S11";
    private static final Phone PHONE = Phone.builder().brand("Samsung").model("S11").build();

    @Test
    public void searchByBrand() {
        searchPhonePredicate = new SearchPhonePredicate(SEARCH_BY_BRAND);

        boolean test = searchPhonePredicate.test(PHONE);

        assertTrue(test);
    }

    @Test
    public void searchByModel() {
        searchPhonePredicate = new SearchPhonePredicate(SEARCH_BY_MODEL);

        boolean test = searchPhonePredicate.test(PHONE);

        assertTrue(test);
    }
}
