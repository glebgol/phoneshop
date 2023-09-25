package com.es.core.model.phone.setextractors;

import com.es.core.model.phone.Color;
import com.es.core.model.phone.Phone;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.es.core.model.phone.setextractors.utll.ResultSetExtractorUtil.getPhone;

@Component
public class PhoneResultSetExtractor implements ResultSetExtractor<Phone> {
    @Override
    public Phone extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Phone phone = null;
        if (resultSet.next()) {
            phone = getPhone(resultSet);
            addColorToPhone(resultSet, phone);

            while (resultSet.next()) {
                addColorToPhone(resultSet, phone);
            }
        }

        return phone;
    }

    private void addColorToPhone(ResultSet resultSet, Phone phone) throws SQLException {
        phone.getColors().add(getColor(resultSet));
    }

    private Color getColor(ResultSet resultSet) throws SQLException {
        String colorCode = resultSet.getString("code");
        Long colorId = resultSet.getLong("colorId");
        return new Color(colorId, colorCode);
    }
}
