package com.es.core.model.phone.dao.impl;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortField;
import com.es.core.model.phone.SortOrder;
import com.es.core.model.phone.dao.PhoneDao;
import com.es.core.model.phone.setextractors.PhoneListResultSetExtractor;
import com.es.core.model.phone.setextractors.PhoneResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class JdbcPhoneDao implements PhoneDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PhoneListResultSetExtractor phoneListResultSetExtractor;
    private final PhoneResultSetExtractor phoneResultSetExtractor;

    public JdbcPhoneDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, PhoneListResultSetExtractor phoneListResultSetExtractor,
                        PhoneResultSetExtractor phoneResultSetExtractor) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.phoneListResultSetExtractor = phoneListResultSetExtractor;
        this.phoneResultSetExtractor = phoneResultSetExtractor;
    }

    private static final String LIMIT_OFFSET = "LIMIT :limit OFFSET :offset";
    private static final String SELECT_PHONES_WITH_COLORS = """
            SELECT p.*, p2c.colorId, c.code FROM phones p
            LEFT JOIN phone2color p2c ON p.id = p2c.phoneId
            LEFT JOIN colors c ON c.id = p2c.colorId
            """;
    private static final String SELECT_PHONES_WITH_COLORS_WITH_LIMIT_AND_OFFSET = SELECT_PHONES_WITH_COLORS +
            "WHERE p.id IN (SELECT id FROM phones " + LIMIT_OFFSET + ")";
    private static final String SELECT_PHONE_BY_ID_WITH_COLORS = """
            SELECT p.*, p2c.*, c.code FROM phones p
            LEFT JOIN phone2color p2c ON p.id = p2c.phoneId
            LEFT JOIN colors c ON c.id = p2c.colorId
            WHERE p.id = :id
            """;
    private static final String SELECT_PHONE_BY_MODEL = """
            SELECT p.*, p2c.*, c.code FROM phones p
            LEFT JOIN phone2color p2c ON p.id = p2c.phoneId
            LEFT JOIN colors c ON c.id = p2c.colorId
            WHERE p.model = :model
            """;
    private static final String SELECT_IN_STOCK_PHONES = """
            SELECT p.*, p2c.*, c.code FROM 
            (SELECT p.* FROM phones p JOIN stocks s ON p.id = s.phoneId WHERE s.stock > 0 
            AND p.price IS NOT NULL LIMIT :limit OFFSET :offset) p
             LEFT JOIN phone2color p2c ON p.id = p2c.phoneId 
             LEFT JOIN colors c ON c.id = p2c.colorId
            """;
    private static final String INSERT_PHONE = """
            INSERT INTO phones (brand, model, price, displaySizeInches, weightGr, lengthMm, widthMm, heightMm, 
            announced, deviceType, os, displayResolution, pixelDensity, displayTechnology, backCameraMegapixels, 
            frontCameraMegapixels, ramGb, internalStorageGb, batteryCapacityMah, talkTimeHours, standByTimeHours,
            bluetooth, positioning, imageUrl, description) VALUES (:brand, :model, :price, :displaySizeInches, 
            :weightGr, :lengthMm, :widthMm, :heightMm, :announced, :deviceType, :os, :displayResolution, :pixelDensity, 
            :displayTechnology, :backCameraMegapixels, :frontCameraMegapixels, :ramGb, :internalStorageGb, 
            :batteryCapacityMah, :talkTimeHours, :standByTimeHours, :bluetooth, :positioning, :imageUrl, :description); 
            """;
    private static final String FILTER_BY_QUERY = """
            (IsNull(:query, '') = '' OR UPPER(p.brand) LIKE UPPER(CONCAT('%', :query, '%'))
             OR UPPER(p.model) LIKE UPPER(CONCAT('%', :query, '%')))
            """;
    private static final String SELECT_PHONES_BY_QUERY = """
            SELECT p.*, p2c.*, c.code FROM 
            (SELECT p.* FROM phones p JOIN stocks s ON p.id = s.phoneId WHERE s.stock > 0
             AND p.price IS NOT NULL
             AND """ + FILTER_BY_QUERY +
            """
             LIMIT :limit OFFSET :offset) p 
                LEFT JOIN phone2color p2c ON p.id = p2c.phoneId 
                LEFT JOIN colors c ON c.id = p2c.colorId
            """;
    private static final String SELECT_PHONES_COUNT_BY_QUERY = """
            SELECT COUNT(*) FROM phones p
            WHERE (SELECT stock FROM stocks WHERE phoneId = p.id) > 0
            AND p.price IS NOT NULL
            AND 
            """ + FILTER_BY_QUERY;
    private static final String INSERT_COLORS = "INSERT INTO phone2color (phoneId, colorId) VALUES ";

    @Override
    public Optional<Phone> get(final Long key) {
        return Optional.ofNullable(namedParameterJdbcTemplate.query(SELECT_PHONE_BY_ID_WITH_COLORS,
                Map.of("id", key), phoneResultSetExtractor));
    }

    @Override
    public Optional<Phone> get(String model) {
        return Optional.ofNullable(namedParameterJdbcTemplate.query(SELECT_PHONE_BY_MODEL,
                Map.of("model", model), phoneResultSetExtractor));
    }

    @Override
    @Transactional
    public void save(final Phone phone) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_PHONE, new BeanPropertySqlParameterSource(phone), keyHolder);
        phone.setId(keyHolder.getKey().longValue());
        saveColors(phone);
    }

    @Override
    public List<Phone> findAll(int offset, int limit) {
        return namedParameterJdbcTemplate.query(SELECT_PHONES_WITH_COLORS_WITH_LIMIT_AND_OFFSET,
                Map.of("limit", limit, "offset", offset), phoneListResultSetExtractor);
    }

    @Override
    public List<Phone> findAllInStock(int offset, int limit) {
        return namedParameterJdbcTemplate.query(SELECT_IN_STOCK_PHONES,
                Map.of("limit", limit, "offset", offset), phoneListResultSetExtractor);
    }

    @Override
    public List<Phone> findAllInStock(String query, int offset, int limit) {
        if (isBlank(query)) {
            return findAllInStock(offset, limit);
        }

        return namedParameterJdbcTemplate.query(SELECT_PHONES_BY_QUERY, Map.of("query", query, "limit", limit,
                "offset", offset), phoneListResultSetExtractor);
    }

    @Override
    public List<Phone> findAllInStock(String query, SortField sortField, SortOrder sortOrder, int offset, int limit) {
        if (sortField == null || sortOrder == null) {
            return findAllInStock(query, offset, limit);
        }

        return namedParameterJdbcTemplate.query(getQuery(sortOrder), Map.of("query", query, "limit",
                        limit, "sortField", getSortField(sortField), "offset",
                        offset), phoneListResultSetExtractor);
    }

    @Override
    public int countPhones(String query) {
        return namedParameterJdbcTemplate.queryForObject(SELECT_PHONES_COUNT_BY_QUERY, Map.of("query", query),
                Integer.class);
    }

    private static String getQuery(SortOrder sortOrder) {
        return  """
            SELECT p.*, p2c.*, c.code FROM 
            (SELECT p.* FROM phones p JOIN stocks s ON p.id = s.phoneId WHERE s.stock > 0
             AND p.price IS NOT NULL
             AND """ + FILTER_BY_QUERY + """ 
             ORDER BY :sortField 
            """
                + sortOrder.name() +
            """
             LIMIT :limit OFFSET :offset) p 
            LEFT JOIN phone2color p2c ON p.id = p2c.phoneId 
            LEFT JOIN colors c ON c.id = p2c.colorId
            """;
    }

    private void saveColors(Phone phone) {
        namedParameterJdbcTemplate.update(INSERT_COLORS + getColorValuesString(phone), Map.of());
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

    private String getSortField(SortField sortField) {
        if (sortField == SortField.DISPLAY_SIZE) {
            return "displaySizeInches";
        } else {
            return sortField.name();
        }
    }
}
