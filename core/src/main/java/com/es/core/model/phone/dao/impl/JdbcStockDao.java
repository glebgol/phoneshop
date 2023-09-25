package com.es.core.model.phone.dao.impl;

import com.es.core.model.phone.Stock;
import com.es.core.model.phone.dao.StockDao;
import com.es.core.model.phone.setextractors.StockResultSetExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcStockDao implements StockDao {
    private final JdbcTemplate jdbcTemplate;
    private final StockResultSetExtractor stockResultSetExtractor;

    public JdbcStockDao(JdbcTemplate jdbcTemplate, StockResultSetExtractor stockResultSetExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.stockResultSetExtractor = stockResultSetExtractor;
    }

    private static final String SELECT_STOCK_BY_PHONE_ID = """
            SELECT * FROM stocks s
            JOIN phones p ON s.phoneId = p.id
            WHERE s.phoneId = ?
            """;
    private static final String UPDATE_STOCK = "UPDATE stocks SET stock = stock - ? WHERE phoneId = ?";

    @Override
    public Stock getByPhoneId(Long phoneId) {
        return jdbcTemplate.query(SELECT_STOCK_BY_PHONE_ID, stockResultSetExtractor, phoneId);
    }

    @Override
    public void reduceStock(Long phoneId, Long quantity) {
        jdbcTemplate.update(UPDATE_STOCK, quantity, phoneId);
    }
}
