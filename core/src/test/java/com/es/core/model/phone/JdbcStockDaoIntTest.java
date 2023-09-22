package com.es.core.model.phone;

import com.es.core.model.phone.dao.StockDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:context/applicationContext-core-test.xml")
public class JdbcStockDaoIntTest {
    @Autowired
    StockDao stockDao;

    private static final Long NON_EXISTING_PHONE_ID = 103L;
    private static final Long EXISTING_PHONE_ID = 1001L;

    @Test
    public void getNonExistentPhone() {
        Optional<Stock> stock = stockDao.getByPhoneId(NON_EXISTING_PHONE_ID);

        assertFalse(stock.isPresent());
    }

    @Test
    public void getExistingPhone() {
        Optional<Stock> stock = stockDao.getByPhoneId(EXISTING_PHONE_ID);

        assertTrue(stock.isPresent());
        assertEquals(EXISTING_PHONE_ID, stock.get().getPhone().getId());
    }
}
