package com.backend.spades.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "addresses")
public class Address extends AbstractPersistable<Long> {

    private String city;

    private String street;

    private Integer number;

    private String zipcode;

    public Address(String city, String street, Integer number, String zipcode) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
    }

    public Address() {

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
