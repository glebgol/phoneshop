package com.es.core.model.phone.setextractors.utll;

import com.es.core.model.phone.Phone;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class ResultSetExtractorUtil {
    public static Phone getPhone(ResultSet resultSet) throws SQLException {
        return Phone.builder()
                .id(resultSet.getLong("id"))
                .brand(resultSet.getString("brand"))
                .model(resultSet.getString("model"))
                .price(resultSet.getBigDecimal("price"))
                .displaySizeInches(resultSet.getBigDecimal("displaySizeInches"))
                .weightGr(resultSet.getInt("weightGr"))
                .lengthMm(resultSet.getBigDecimal("lengthMm"))
                .widthMm(resultSet.getBigDecimal("widthMm"))
                .heightMm(resultSet.getBigDecimal("heightMm"))
                .announced(resultSet.getDate("announced"))
                .deviceType(resultSet.getString("deviceType"))
                .os(resultSet.getString("os"))
                .displayResolution(resultSet.getString("displayResolution"))
                .pixelDensity(resultSet.getInt("pixelDensity"))
                .displayTechnology(resultSet.getString("displayTechnology"))
                .backCameraMegapixels(resultSet.getBigDecimal("backCameraMegapixels"))
                .frontCameraMegapixels(resultSet.getBigDecimal("frontCameraMegapixels"))
                .ramGb(resultSet.getBigDecimal("ramGb"))
                .internalStorageGb(resultSet.getBigDecimal("internalStorageGb"))
                .batteryCapacityMah(resultSet.getInt("batteryCapacityMah"))
                .talkTimeHours(resultSet.getBigDecimal("talkTimeHours"))
                .standByTimeHours(resultSet.getBigDecimal("standByTimeHours"))
                .bluetooth(resultSet.getString("bluetooth"))
                .positioning(resultSet.getString("positioning"))
                .imageUrl(resultSet.getString("imageUrl"))
                .description(resultSet.getString("description"))
                .colors(new HashSet<>())
                .build();
    }
}
