package com.es.core.model.phone.setextractors;

import com.es.core.model.phone.Color;
import com.es.core.model.phone.Phone;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.es.core.model.phone.setextractors.utll.ResultSetExtractorUtil.getPhone;

@Component
public class PhoneListResultSetExtractor implements ResultSetExtractor<List<Phone>> {
    @Override
    public List<Phone> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, Phone> phones = new LinkedHashMap<>();

        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            Phone phone = phones.get(id);

            if (phone == null) {
                phone = getPhone(resultSet);
                phones.put(id, phone);
            }

            addColorToPhone(phone, resultSet);
        }

        return new ArrayList<>(phones.values());
    }

    private void addColorToPhone(Phone phone, ResultSet resultSet) throws SQLException {
        phone.getColors().add(getColor(resultSet));
    }

    private Color getColor(ResultSet resultSet) throws SQLException {
        String colorCode = resultSet.getString("code");
        Long colorId = resultSet.getLong("colorId");
        return new Color(colorId, colorCode);
    }
}
