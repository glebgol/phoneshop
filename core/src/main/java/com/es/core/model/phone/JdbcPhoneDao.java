package com.es.core.model.phone;

import com.es.core.model.phone.setextractors.PhoneListResultSetExtractor;
import com.es.core.model.phone.setextractors.PhoneResultSetExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcPhoneDao implements PhoneDao {
    private final JdbcTemplate jdbcTemplate;
    private final PhoneListResultSetExtractor phoneListResultSetExtractor;
    private final PhoneResultSetExtractor phoneResultSetExtractor;

    public JdbcPhoneDao(JdbcTemplate jdbcTemplate, PhoneListResultSetExtractor phoneListResultSetExtractor, PhoneResultSetExtractor phoneResultSetExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.phoneListResultSetExtractor = phoneListResultSetExtractor;
        this.phoneResultSetExtractor = phoneResultSetExtractor;
    }

    private static final String SELECT_PHONES_WITH_COLORS = """
            SELECT p.*, p2c.colorId, c.code FROM phones p
            LEFT JOIN phone2color p2c ON p.id = p2c.phoneId
            LEFT JOIN colors c ON c.id = p2c.colorId
            WHERE p.id IN (SELECT id FROM phones LIMIT ? OFFSET ?) 
            """;
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
    @Transactional
    public List<Phone> findAll(int offset, int limit) {
        return jdbcTemplate.query(SELECT_PHONES_WITH_COLORS, phoneListResultSetExtractor, limit, offset);
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
