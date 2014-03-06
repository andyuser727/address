package exercise.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Contact Entity.
 */
@Entity
@Table(name="CONTACT")
public class Contact extends PersistentObject {

    private String forename;
    private String surname;
    private String street;
    private String city;
    private String postalCode;
    private String county;
    private String country;

    public Contact() {

    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", county='" + county + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
