package com.es.core.model.phone;

import com.es.core.model.phone.dao.PhoneDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:context/applicationContext-core-test.xml")
public class JdbcPhoneDaoIntTest {
    @Autowired
    PhoneDao phoneDao;

    private static final Long NON_EXISTING_PHONE_ID = 103L;
    private static final Long EXISTING_PHONE_ID = 1000L;
    private static final int OFFSET = 0;
    private static final int LIMIT = 10000;
    private static final int MIN_LIMIT = 10;
    private static final Set<Color> COLORS = Set.of(new Color(1000L, "Black"),
            new Color(1001L, "White"), new Color(1002L, "Yellow"));
    private static final String MODEL = "Mi 11 lite";
    private static final String BRAND = "Xiaomi";
    private static final String MODEL1 = "ARCHOS 101 Oxygen";
    private static final Phone PHONE_FOR_SAVE = Phone.builder()
            .model(MODEL).brand(BRAND).colors(COLORS)
            .build();
    private static final Long PHONE_ID = 8764L;

    @Test
    public void getNonExistentPhone() {
        Optional<Phone> phone = phoneDao.get(NON_EXISTING_PHONE_ID);

        assertFalse(phone.isPresent());
    }

    @Test
    public void getExistingPhone() {
        Optional<Phone> phone = phoneDao.get(EXISTING_PHONE_ID);

        assertTrue(phone.isPresent());
        assertEquals(EXISTING_PHONE_ID, phone.get().getId());
    }

    @Test
    public void getExistingPhone2() {
        Optional<Phone> phone = phoneDao.get(MODEL1);

        assertTrue(phone.isPresent());
    }

    @Test
    public void findAllPhonesNotEmpty() {
        List<Phone> phones = phoneDao.findAll(OFFSET, LIMIT);

        assertFalse(phones.isEmpty());
    }

    @Test
    public void verifyCountOfFindingAllPhonesWithOffsetAndLimit() {
        int offset = 5;
        int limit = 10;

        List<Phone> phones = phoneDao.findAll(offset, limit);

        assertEquals(limit, phones.size());
    }

    @Test
    public void savePhone() {
        int countBefore = phoneDao.findAll(OFFSET, LIMIT).size();

        phoneDao.save(PHONE_FOR_SAVE);
        int countAfter = phoneDao.findAll(OFFSET, LIMIT).size();
        Phone phone = phoneDao.get(PHONE_ID).get();

        assertEquals(COLORS.size(), phone.getColors().size());
        assertEquals(countBefore + 1, countAfter);
    }

    @Test
    public void findAllWithPositiveStock() {
        List<Phone> phones = phoneDao.findAllInStock(OFFSET, MIN_LIMIT);

        assertFalse(phones.isEmpty());
    }
}
