package com.es.core.model.phone;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Phone {
    private Long id;
    private String brand;
    private String model;
    private BigDecimal price;
    private BigDecimal displaySizeInches;
    private Integer weightGr;
    private BigDecimal lengthMm;
    private BigDecimal widthMm;
    private BigDecimal heightMm;
    private Date announced;
    private String deviceType;
    private String os;
    private Set<Color> colors = Collections.EMPTY_SET;
    private String displayResolution;
    private Integer pixelDensity;
    private String displayTechnology;
    private BigDecimal backCameraMegapixels;
    private BigDecimal frontCameraMegapixels;
    private BigDecimal ramGb;
    private BigDecimal internalStorageGb;
    private Integer batteryCapacityMah;
    private BigDecimal talkTimeHours;
    private BigDecimal standByTimeHours;
    private String bluetooth;
    private String positioning;
    private String imageUrl;
    private String description;

    public static class PhoneBuilder {
        private Long id;
        private String brand;
        private String model;
        private BigDecimal price;
        private BigDecimal displaySizeInches;
        private Integer weightGr;
        private BigDecimal lengthMm;
        private BigDecimal widthMm;
        private BigDecimal heightMm;
        private Date announced;
        private String deviceType;
        private String os;
        private Set<Color> colors = Collections.EMPTY_SET;
        private String displayResolution;
        private Integer pixelDensity;
        private String displayTechnology;
        private BigDecimal backCameraMegapixels;
        private BigDecimal frontCameraMegapixels;
        private BigDecimal ramGb;
        private BigDecimal internalStorageGb;
        private Integer batteryCapacityMah;
        private BigDecimal talkTimeHours;
        private BigDecimal standByTimeHours;
        private String bluetooth;
        private String positioning;
        private String imageUrl;
        private String description;

        public PhoneBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PhoneBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public PhoneBuilder model(String model) {
            this.model = model;
            return this;
        }

        public PhoneBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public PhoneBuilder displaySizeInches(BigDecimal displaySizeInches) {
            this.displaySizeInches = displaySizeInches;
            return this;
        }

        public PhoneBuilder weightGr(Integer weightGr) {
            this.weightGr = weightGr;
            return this;
        }

        public PhoneBuilder lengthMm(BigDecimal lengthMm) {
            this.lengthMm = lengthMm;
            return this;
        }

        public PhoneBuilder widthMm(BigDecimal widthMm) {
            this.widthMm = widthMm;
            return this;
        }

        public PhoneBuilder heightMm(BigDecimal heightMm) {
            this.heightMm = heightMm;
            return this;
        }

        public PhoneBuilder announced(Date announced) {
            this.announced = announced;
            return this;
        }

        public PhoneBuilder deviceType(String announced) {
            this.deviceType = deviceType;
            return this;
        }

        public PhoneBuilder os(String os) {
            this.os = os;
            return this;
        }

        public PhoneBuilder colors(Set<Color> colors) {
            this.colors = colors;
            return this;
        }

        public PhoneBuilder displayResolution(String displayResolution) {
            this.displayResolution = displayResolution;
            return this;
        }

        public PhoneBuilder pixelDensity(Integer pixelDensity) {
            this.pixelDensity = pixelDensity;
            return this;
        }

        public PhoneBuilder displayTechnology(String displayTechnology) {
            this.displayTechnology = displayTechnology;
            return this;
        }

        public PhoneBuilder backCameraMegapixels(BigDecimal backCameraMegapixels) {
            this.backCameraMegapixels = backCameraMegapixels;
            return this;
        }

        public PhoneBuilder frontCameraMegapixels(BigDecimal frontCameraMegapixels) {
            this.frontCameraMegapixels = frontCameraMegapixels;
            return this;
        }

        public PhoneBuilder ramGb(BigDecimal ramGb) {
            this.ramGb = ramGb;
            return this;
        }

        public PhoneBuilder internalStorageGb(BigDecimal internalStorageGb) {
            this.internalStorageGb = internalStorageGb;
            return this;
        }

        public PhoneBuilder batteryCapacityMah(Integer batteryCapacityMah) {
            this.batteryCapacityMah = batteryCapacityMah;
            return this;
        }

        public PhoneBuilder talkTimeHours(BigDecimal talkTimeHours) {
            this.talkTimeHours = talkTimeHours;
            return this;
        }

        public PhoneBuilder standByTimeHours(BigDecimal standByTimeHours) {
            this.standByTimeHours = standByTimeHours;
            return this;
        }

        public PhoneBuilder bluetooth(String bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }

        public PhoneBuilder positioning(String positioning) {
            this.positioning = positioning;
            return this;
        }

        public PhoneBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public PhoneBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Phone build() {
            return new Phone(id, brand, model, price, displaySizeInches, weightGr, lengthMm, widthMm, heightMm,
                    announced, deviceType, os, colors, displayResolution, pixelDensity, displayTechnology,
                     backCameraMegapixels, frontCameraMegapixels, ramGb, internalStorageGb,  batteryCapacityMah,
                    talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, description);
        }
    }

    public static PhoneBuilder builder() {
        return new PhoneBuilder();
    }

    public Phone() {
    }

    public Phone(Long id, String brand, String model, BigDecimal price, BigDecimal displaySizeInches, Integer weightGr,
                 BigDecimal lengthMm, BigDecimal widthMm, BigDecimal heightMm, Date announced, String deviceType,
                 String os, Set<Color> colors, String displayResolution, Integer pixelDensity, String displayTechnology,
                 BigDecimal backCameraMegapixels, BigDecimal frontCameraMegapixels, BigDecimal ramGb,
                 BigDecimal internalStorageGb, Integer batteryCapacityMah, BigDecimal talkTimeHours,
                 BigDecimal standByTimeHours, String bluetooth, String positioning, String imageUrl,
                 String description) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.displaySizeInches = displaySizeInches;
        this.weightGr = weightGr;
        this.lengthMm = lengthMm;
        this.widthMm = widthMm;
        this.heightMm = heightMm;
        this.announced = announced;
        this.deviceType = deviceType;
        this.os = os;
        this.colors = colors;
        this.displayResolution = displayResolution;
        this.pixelDensity = pixelDensity;
        this.displayTechnology = displayTechnology;
        this.backCameraMegapixels = backCameraMegapixels;
        this.frontCameraMegapixels = frontCameraMegapixels;
        this.ramGb = ramGb;
        this.internalStorageGb = internalStorageGb;
        this.batteryCapacityMah = batteryCapacityMah;
        this.talkTimeHours = talkTimeHours;
        this.standByTimeHours = standByTimeHours;
        this.bluetooth = bluetooth;
        this.positioning = positioning;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDisplaySizeInches() {
        return displaySizeInches;
    }

    public void setDisplaySizeInches(BigDecimal displaySizeInches) {
        this.displaySizeInches = displaySizeInches;
    }

    public Integer getWeightGr() {
        return weightGr;
    }

    public void setWeightGr(Integer weightGr) {
        this.weightGr = weightGr;
    }

    public BigDecimal getLengthMm() {
        return lengthMm;
    }

    public void setLengthMm(BigDecimal lengthMm) {
        this.lengthMm = lengthMm;
    }

    public BigDecimal getWidthMm() {
        return widthMm;
    }

    public void setWidthMm(BigDecimal widthMm) {
        this.widthMm = widthMm;
    }

    public BigDecimal getHeightMm() {
        return heightMm;
    }

    public void setHeightMm(BigDecimal heightMm) {
        this.heightMm = heightMm;
    }

    public Date getAnnounced() {
        return announced;
    }

    public void setAnnounced(Date announced) {
        this.announced = announced;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public void setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
    }

    public Integer getPixelDensity() {
        return pixelDensity;
    }

    public void setPixelDensity(Integer pixelDensity) {
        this.pixelDensity = pixelDensity;
    }

    public String getDisplayTechnology() {
        return displayTechnology;
    }

    public void setDisplayTechnology(String displayTechnology) {
        this.displayTechnology = displayTechnology;
    }

    public BigDecimal getBackCameraMegapixels() {
        return backCameraMegapixels;
    }

    public void setBackCameraMegapixels(BigDecimal backCameraMegapixels) {
        this.backCameraMegapixels = backCameraMegapixels;
    }

    public BigDecimal getFrontCameraMegapixels() {
        return frontCameraMegapixels;
    }

    public void setFrontCameraMegapixels(BigDecimal frontCameraMegapixels) {
        this.frontCameraMegapixels = frontCameraMegapixels;
    }

    public BigDecimal getRamGb() {
        return ramGb;
    }

    public void setRamGb(BigDecimal ramGb) {
        this.ramGb = ramGb;
    }

    public BigDecimal getInternalStorageGb() {
        return internalStorageGb;
    }

    public void setInternalStorageGb(BigDecimal internalStorageGb) {
        this.internalStorageGb = internalStorageGb;
    }

    public Integer getBatteryCapacityMah() {
        return batteryCapacityMah;
    }

    public void setBatteryCapacityMah(Integer batteryCapacityMah) {
        this.batteryCapacityMah = batteryCapacityMah;
    }

    public BigDecimal getTalkTimeHours() {
        return talkTimeHours;
    }

    public void setTalkTimeHours(BigDecimal talkTimeHours) {
        this.talkTimeHours = talkTimeHours;
    }

    public BigDecimal getStandByTimeHours() {
        return standByTimeHours;
    }

    public void setStandByTimeHours(BigDecimal standByTimeHours) {
        this.standByTimeHours = standByTimeHours;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getPositioning() {
        return positioning;
    }

    public void setPositioning(String positioning) {
        this.positioning = positioning;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) && Objects.equals(brand, phone.brand) && Objects.equals(model, phone.model)
                && Objects.equals(price, phone.price) && Objects.equals(displaySizeInches, phone.displaySizeInches)
                && Objects.equals(weightGr, phone.weightGr) && Objects.equals(lengthMm, phone.lengthMm)
                && Objects.equals(widthMm, phone.widthMm) && Objects.equals(heightMm, phone.heightMm)
                && Objects.equals(announced, phone.announced) && Objects.equals(deviceType, phone.deviceType)
                && Objects.equals(os, phone.os) && Objects.equals(colors, phone.colors)
                && Objects.equals(displayResolution, phone.displayResolution)
                && Objects.equals(pixelDensity, phone.pixelDensity)
                && Objects.equals(displayTechnology, phone.displayTechnology)
                && Objects.equals(backCameraMegapixels, phone.backCameraMegapixels)
                && Objects.equals(frontCameraMegapixels, phone.frontCameraMegapixels)
                && Objects.equals(ramGb, phone.ramGb) && Objects.equals(internalStorageGb, phone.internalStorageGb)
                && Objects.equals(batteryCapacityMah, phone.batteryCapacityMah) && Objects.equals(talkTimeHours,
                phone.talkTimeHours) && Objects.equals(standByTimeHours, phone.standByTimeHours) &&
                Objects.equals(bluetooth, phone.bluetooth) && Objects.equals(positioning, phone.positioning)
                && Objects.equals(imageUrl, phone.imageUrl) && Objects.equals(description, phone.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, price, displaySizeInches, weightGr, lengthMm, widthMm, heightMm,
                announced, deviceType, os, colors, displayResolution, pixelDensity, displayTechnology,
                backCameraMegapixels, frontCameraMegapixels, ramGb, internalStorageGb, batteryCapacityMah,
                talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, description);
    }
}
