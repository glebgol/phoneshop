package com.es.core.model.phone.setextractors;

import com.es.core.model.phone.Stock;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.es.core.model.phone.setextractors.utll.ResultSetExtractorUtil.getPhone;

@Component
public class StockResultSetExtractor implements ResultSetExtractor<Stock> {
    @Override
    public Stock extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Stock stock = null;

        if (resultSet.next()) {
            stock = new Stock();
            stock.setStock(resultSet.getInt("stock"));
            stock.setReserved(resultSet.getInt("reserved"));
            stock.setPhone(getPhone(resultSet));
        }

        return stock;
    }
}
