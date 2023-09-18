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
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
public class PhoneListResultSetExtractor implements ResultSetExtractor<List<Phone>> {
    @Override
    public List<Phone> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, Phone> phones = new LinkedHashMap<>();

        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            Phone phone = phones.get(id);

            if (phone == null) {
                phone = getPhone(id, resultSet);
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

    private Phone getPhone(Long id, ResultSet resultSet) throws SQLException {
        return Phone.builder()
                .id(id)
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
                .ramGb(resultSet.getBigDecimal("ramGb"))
                .model(resultSet.getString("model"))
                .build();
    }
}
