package com.es.core.model.phone.predicates;

import com.es.core.model.phone.Phone;

import java.util.function.Predicate;

public class SearchPhonePredicate implements Predicate<Phone> {
    private final String query;

    public SearchPhonePredicate(String query) {
        this.query = query;
    }

    @Override
    public boolean test(Phone phone) {
        return query != null && (phone.getBrand().toLowerCase().contains(query.toLowerCase()) ||
                phone.getModel().toLowerCase().contains(query.toLowerCase()) ||
                phone.getDescription().toLowerCase().contains(query.toLowerCase()) ||
                phone.getDeviceType().equalsIgnoreCase(query));
    }
}
