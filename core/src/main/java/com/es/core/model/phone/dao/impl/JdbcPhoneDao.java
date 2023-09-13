package com.es.core.model.phone.dao.impl;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortField;
import com.es.core.model.phone.SortOrder;
import com.es.core.model.phone.Stock;
import com.es.core.model.phone.comparators.PhoneComparatorFactory;
import com.es.core.model.phone.dao.PhoneDao;
import com.es.core.model.phone.dao.StockDao;
import com.es.core.model.phone.predicates.SearchPhonePredicate;
import com.es.core.model.phone.setextractors.PhoneListResultSetExtractor;
import com.es.core.model.phone.setextractors.PhoneResultSetExtractor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JdbcPhoneDao implements PhoneDao {
    private final JdbcTemplate jdbcTemplate;
    private final PhoneListResultSetExtractor phoneListResultSetExtractor;
    private final PhoneResultSetExtractor phoneResultSetExtractor;
    private final StockDao stockDao;

    public JdbcPhoneDao(JdbcTemplate jdbcTemplate, PhoneListResultSetExtractor phoneListResultSetExtractor,
                        PhoneResultSetExtractor phoneResultSetExtractor, StockDao stockDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.phoneListResultSetExtractor = phoneListResultSetExtractor;
        this.phoneResultSetExtractor = phoneResultSetExtractor;
        this.stockDao = stockDao;
    }

    private static final String SELECT_PHONES_WITH_COLORS = """
            SELECT p.*, p2c.colorId, c.code FROM phones p
            LEFT JOIN phone2color p2c ON p.id = p2c.phoneId
            LEFT JOIN colors c ON c.id = p2c.colorId
            """;
    private static final String SELECT_PHONES_WITH_COLORS_WITH_LIMIT_AND_OFFSET = SELECT_PHONES_WITH_COLORS +
            "WHERE p.id IN (SELECT id FROM phones LIMIT ? OFFSET ?)";
    private static final String SELECT_PHONE_BY_ID_WITH_COLORS = """
            SELECT p.*, p2c.*, c.code FROM phones p
            LEFT JOIN phone2color p2c ON p.id = p2c.phoneId
            LEFT JOIN colors c ON c.id = p2c.colorId
            WHERE p.id = ?
            """;
    private static final String INSERT_PHONE = """
            INSERT INTO phones (brand, model, price, displaySizeInches, weightGr, lengthMm, widthMm, heightMm, 
            announced, deviceType, os, displayResolution, pixelDensity, displayTechnology, backCameraMegapixels, 
            frontCameraMegapixels, ramGb, internalStorageGb, batteryCapacityMah, talkTimeHours, standByTimeHours,
            bluetooth, positioning, imageUrl, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); 
            """;
    private static final String INSERT_COLORS = "INSERT INTO phone2color (phoneId, colorId) VALUES ";
    public static final String SELECT_COUNT_FROM_PHONES = "SELECT COUNT(*) FROM phones";
    public static final String SELECT_PHONES = "SELECT * FROM phones";

    @Override
    public Optional<Phone> get(final Long key) {
        return Optional.ofNullable(jdbcTemplate.query(SELECT_PHONE_BY_ID_WITH_COLORS, phoneResultSetExtractor, key));
    }

    @Override
    @Transactional
    public void save(final Phone phone) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_PHONE);
            setArguments(phone, ps);
            return ps;
        }, keyHolder);

        phone.setId(keyHolder.getKey().longValue());
        saveColors(phone);
    }

    @Override
    public List<Phone> findAll(int offset, int limit) {
        return jdbcTemplate.query(SELECT_PHONES_WITH_COLORS_WITH_LIMIT_AND_OFFSET, phoneListResultSetExtractor,
                limit, offset);
    }

    @Override
    public List<Phone> findAllWithPositiveStock(int offset, int limit) {
        return getPhoneStreamWithPositiveStock(offset, limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Phone> findAllWithPositiveStock(SortField sortField, SortOrder sortOrder, int offset, int limit) {
        return getPhoneStreamWithPositiveStock(offset, limit)
                .sorted(PhoneComparatorFactory.createComparator(sortField, sortOrder))
                .collect(Collectors.toList());
    }

    @Override
    public List<Phone> findAllByQueryWithPositiveStock(String query, SortField sortField, SortOrder sortOrder,
                                                       int offset, int limit) {
        return jdbcTemplate.query(SELECT_PHONES_WITH_COLORS, phoneListResultSetExtractor).stream()
                .filter(new SearchPhonePredicate(query))
                .filter(this::isPositivePhoneStock)
                .skip(offset)
                .limit(limit)
                .sorted(PhoneComparatorFactory.createComparator(sortField, sortOrder))
                .collect(Collectors.toList());
    }

    @Override
    public int countPhones(String query) {
        return (int) jdbcTemplate.query(SELECT_PHONES, new BeanPropertyRowMapper(Phone.class)).stream()
                .filter(new SearchPhonePredicate(query))
                .count();
    }

    @Override
    public int countPhones() {
        return jdbcTemplate.queryForObject(SELECT_COUNT_FROM_PHONES, Integer.class);
    }

    private Stream<Phone> getPhoneStreamWithPositiveStock(int offset, int limit) {
        return jdbcTemplate.query(SELECT_PHONES_WITH_COLORS, phoneListResultSetExtractor).stream()
                .filter(this::isPositivePhoneStock)
                .skip(offset)
                .limit(limit);
    }

    private boolean isPositivePhoneStock(Phone phone) {
        Optional<Stock> stock = stockDao.get(phone.getId());
        return stock.isPresent() && stock.get().getStock() > 0;
    }

    private void saveColors(Phone phone) {
        jdbcTemplate.update(INSERT_COLORS + getColorValuesString(phone));
    }

    private String getColorValuesString(Phone phone) {
        StringBuilder stringBuilder = new StringBuilder();
        String partWithPhoneId = "(" + phone.getId() + ", ";
        String endOfValue = "), ";

        phone.getColors().forEach(color -> stringBuilder.append(partWithPhoneId)
                .append(color.getId())
                .append(endOfValue)
        );
        stringBuilder.delete(stringBuilder.lastIndexOf(", "), stringBuilder.length());
        return stringBuilder.toString();
    }

    private static void setArguments(Phone phone, PreparedStatement ps) throws SQLException {
        ps.setString(1, phone.getBrand());
        ps.setString(2, phone.getModel());
        ps.setBigDecimal(3, phone.getPrice());
        ps.setBigDecimal(4, phone.getDisplaySizeInches());
        if (phone.getWeightGr() == null) {
            ps.setString(5, null);
        } else {
            ps.setInt(5, phone.getWeightGr());
        }
        ps.setBigDecimal(6, phone.getLengthMm());
        ps.setBigDecimal(7, phone.getWidthMm());
        ps.setBigDecimal(8, phone.getHeightMm());
        if (phone.getAnnounced() == null) {
            ps.setString(9, null);
        } else {
            ps.setDate(9, new java.sql.Date(phone.getAnnounced().getTime()));
        }
        ps.setString(10, phone.getDeviceType());
        ps.setString(11, phone.getOs());
        ps.setString(12, phone.getDisplayResolution());
        if (phone.getPixelDensity() == null) {
            ps.setString(13, null);
        } else {
            ps.setInt(13, phone.getPixelDensity());
        }
        ps.setString(14, phone.getDisplayTechnology());
        ps.setBigDecimal(15, phone.getBackCameraMegapixels());
        ps.setBigDecimal(16, phone.getFrontCameraMegapixels());
        ps.setBigDecimal(17, phone.getRamGb());
        ps.setBigDecimal(18, phone.getInternalStorageGb());
        if (phone.getBatteryCapacityMah() == null) {
            ps.setString(19, null);
        } else {
            ps.setInt(19, phone.getBatteryCapacityMah());
        }
        ps.setBigDecimal(20, phone.getTalkTimeHours());
        ps.setBigDecimal(21, phone.getStandByTimeHours());
        ps.setString(22, phone.getBluetooth());
        ps.setString(23, phone.getPositioning());
        ps.setString(24, phone.getImageUrl());
        ps.setString(25, phone.getDescription());
    }
}
