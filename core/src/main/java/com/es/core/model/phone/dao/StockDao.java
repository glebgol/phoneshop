package com.es.core.model.phone.dao;

import com.es.core.model.phone.Stock;

import java.util.Optional;

public interface StockDao {
    Optional<Stock> getByPhoneId(Long phoneId);
}
