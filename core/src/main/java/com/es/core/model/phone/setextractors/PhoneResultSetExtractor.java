package com.es.core.model.phone.setextractors;

import com.es.core.model.phone.Color;
import com.es.core.model.phone.Phone;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

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

    private Phone getPhone(ResultSet resultSet) throws SQLException {
        return Phone.builder()
                .id(resultSet.getLong("id"))
                .colors(new HashSet<>())
                .announced(resultSet.getDate("announced"))
                .backCameraMegapixels(resultSet.getBigDecimal("backCameraMegapixels"))
                .batteryCapacityMah(resultSet.getInt("batteryCapacityMah"))
                .bluetooth(resultSet.getString("bluetooth"))
                .brand(resultSet.getString("brand"))
                .description(resultSet.getString("description"))
                .displayResolution(resultSet.getString("displayResolution"))
                .displaySizeInches(resultSet.getBigDecimal("displaySizeInches"))
                .displayTechnology(resultSet.getString("displayTechnology"))
                .frontCameraMegapixels(resultSet.getBigDecimal("frontCameraMegapixels"))
                .deviceType(resultSet.getString("deviceType"))
                .imageUrl(resultSet.getString("imageUrl"))
                .internalStorageGb(resultSet.getBigDecimal("internalStorageGb"))
                .os(resultSet.getString("os"))
                .price(resultSet.getBigDecimal("price"))
                .ramGb(resultSet.getBigDecimal("ramGb"))
                .positioning(resultSet.getString("positioning"))
                .pixelDensity(resultSet.getInt("pixelDensity"))
                .lengthMm(resultSet.getBigDecimal("lengthMm"))
                .standByTimeHours(resultSet.getBigDecimal("standByTimeHours"))
                .talkTimeHours(resultSet.getBigDecimal("talkTimeHours"))
                .weightGr(resultSet.getInt("weightGr"))
                .heightMm(resultSet.getBigDecimal("heightMm"))
                .widthMm(resultSet.getBigDecimal("widthMm"))
                .model(resultSet.getString("model"))
                .build();
    }
}
