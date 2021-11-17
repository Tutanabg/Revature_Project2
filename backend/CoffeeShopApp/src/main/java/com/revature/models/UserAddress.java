package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "user_addresses")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", updatable = false)
    private int addressID;

    @Column(name = "zip_code")
    private int zipCode;

    private String street;
    private String city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserAddress() {
    }

    public UserAddress(int zipCode, String street, String city, User user) {
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
        this.user = user;
    }

    public UserAddress(int addressID, int zipCode, String street, String city, User user) {
        this.addressID = addressID;
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
        this.user = user;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "addressID=" + addressID +
                ", zipCode=" + zipCode +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", user=" + user +
                '}';
    }
}
