package com.es.core.model.phone.dao;

import com.es.core.model.phone.Stock;

public interface StockDao {
    Stock getByPhoneId(Long phoneId);
    void reduceStock(Long phoneId, Long quantity);
}
