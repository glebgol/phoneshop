package com.es.phoneshop.web.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrderForm {
    @NotBlank(message = "Empty value")
    @Size(max = 30, message = "Too long First Name")
    @Pattern(regexp = "^[a-zA-Z .]+", message = "Not valid")
    private String firstName;

    @NotBlank(message = "Empty value")
    @Pattern(regexp = "^[a-zA-Z .]+", message = "not valid")
    @Size(max = 30, message = "Too long Last Name")
    private String lastName;

    @NotBlank(message = "Empty value")
    @Pattern(regexp = "^([a-zA-z0-9/\\\\''(),\\s]{2,255})$", message = "not valid")
    @Size(max = 200, message = "Too long Address")
    private String deliveryAddress;

    @NotBlank(message = "Empty value")
    @Pattern(regexp = "^([+]?[\\s0-9]+)?(\\d{3}|[(]?[0-9]+[)])?(-?\\s?[0-9])+$", message = "not valid")
    @Size(max = 20, message = "Not valid")
    private String contactPhoneNo;

    @Size(max = 4096, message = "Too much :)")
    private String additionalInfo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
