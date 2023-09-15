package com.es.core.model.phone.dao;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortField;
import com.es.core.model.phone.SortOrder;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Optional<Phone> get(Long key);
    void save(Phone phone);
    List<Phone> findAll(int offset, int limit);
    List<Phone> findAllInStock(int offset, int limit);
    List<Phone> findAllInStock(String query, SortField sortField, SortOrder sortOrder, int offset,
                               int limit);
    List<Phone> findAllInStock(String query, int offset,
                               int limit);
    int countPhones(String query);
}
