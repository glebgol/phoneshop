package com.es.core.model.phone.setextractors.utll;

import com.es.core.model.phone.Phone;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class ResultSetExtractorUtil {
    public static Phone getPhone(ResultSet resultSet) throws SQLException {
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

    public static Phone getPhone(Long id, ResultSet resultSet) throws SQLException {
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
