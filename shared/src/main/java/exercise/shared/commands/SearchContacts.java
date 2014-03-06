package exercise.shared.commands;

import exercise.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class SearchContacts extends RpcDto{

    private String forename;
    private String surname;
    private String street;
    private String city;
    private String postalCode;
    private String county;
    private String country;

    public SearchContacts(String forename, String surname,
                          String street, String city,
                          String county, String country,
                          String postalCode){
        this.forename = forename;
        this.surname = surname;
        this.street = street;
        this.city = city;
        this.county = county;
        this.country = country;
        this.postalCode = postalCode;
    }

    public SearchContacts(){
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }
}
