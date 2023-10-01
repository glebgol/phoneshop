package com.es.core.model.phone;

import com.es.core.model.phone.dao.StockDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:context/applicationContext-core-test.xml")
public class JdbcStockDaoIntTest {
    @Autowired
    StockDao stockDao;

    private static final Long NON_EXISTING_PHONE_ID = 103L;
    private static final Long EXISTING_PHONE_ID = 1001L;

    @Test
    public void getNonExistentPhone() {
        Stock stock = stockDao.getByPhoneId(NON_EXISTING_PHONE_ID);

        assertNull(stock);
    }

    @Test
    public void getExistingPhone() {
        Stock stock = stockDao.getByPhoneId(EXISTING_PHONE_ID);

        assertNotNull(stock);
        assertEquals(EXISTING_PHONE_ID, stock.getPhone().getId());
    }
}
