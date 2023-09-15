package com.es.core.model.phone.predicates;

import com.es.core.model.phone.Phone;

import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class SearchPhonePredicate implements Predicate<Phone> {
    private final String query;

    public SearchPhonePredicate(String query) {
        this.query = query;
    }

    @Override
    public boolean test(Phone phone) {
        return isBlank(query) || containsIgnoreCase(phone.getModel(), query) ||
                containsIgnoreCase(phone.getBrand(), query);
    }
}
