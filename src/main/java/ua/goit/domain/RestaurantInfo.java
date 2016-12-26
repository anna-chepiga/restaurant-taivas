package ua.goit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_info")
public class RestaurantInfo {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "restaurant_schema_url")
    private String restaurantSchemaUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantSchemaUrl() {
        return restaurantSchemaUrl;
    }

    public void setRestaurantSchemaUrl(String restaurantSchemaUrl) {
        this.restaurantSchemaUrl = restaurantSchemaUrl;
    }

    @Override
    public String toString() {
        return "RestaurantInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", restaurantSchemaUrl='" + restaurantSchemaUrl + '\'' +
                '}';
    }
}
