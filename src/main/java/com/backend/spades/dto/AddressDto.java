package com.backend.spades.dto;

import com.backend.spades.model.TypeScriptGeneration;

@TypeScriptGeneration
public class AddressDto {

    private String city;

    private String street;

    private Integer number;

    private String zipcode;

    public AddressDto(String city, String street, Integer number, String zipcode) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
