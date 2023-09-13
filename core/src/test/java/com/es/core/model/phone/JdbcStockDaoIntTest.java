package com.es.core.model.phone;

import com.es.core.model.phone.dao.StockDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:context/applicationContext-core-test.xml")
public class JdbcStockDaoIntTest {
    @Autowired
    StockDao stockDao;

    private static final Long NON_EXISTING_PHONE_ID = 1L;
    private static final Long EXISTING_PHONE_ID = 1001L;
    private static final Long EXISTING_PHONE_ID_WITHOUT_STOCK = 1000L;

    @Test
    public void getShouldReturnEmptyValueByNotExistingPhoneId() {
        Optional<Stock> stock = stockDao.get(NON_EXISTING_PHONE_ID);

        assertTrue(stock.isEmpty());
    }

    @Test
    public void getShouldReturnPresentValueByExistingPhoneId() {
        Optional<Stock> stock = stockDao.get(EXISTING_PHONE_ID);

        assertTrue(stock.isPresent());
    }

    @Test
    public void getShouldReturnEmptyValueByExistingPhoneIdWithoutStock() {
        Optional<Stock> stock = stockDao.get(EXISTING_PHONE_ID_WITHOUT_STOCK);

        assertTrue(stock.isEmpty());
    }
}
