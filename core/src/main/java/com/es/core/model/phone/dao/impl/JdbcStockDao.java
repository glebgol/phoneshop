package com.es.core.model.phone.dao.impl;

import com.es.core.model.phone.Stock;
import com.es.core.model.phone.dao.StockDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JdbcStockDao implements StockDao {
    private final JdbcTemplate jdbcTemplate;
    public static final String SELECT_FROM_STOCKS_WHERE_PHONE_ID = "SELECT * FROM stocks WHERE phoneId = ?";

    public JdbcStockDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Stock> get(Long phoneId) {
        return jdbcTemplate.query(SELECT_FROM_STOCKS_WHERE_PHONE_ID,
                        new BeanPropertyRowMapper<>(Stock.class), phoneId).stream().findAny();
    }
}
