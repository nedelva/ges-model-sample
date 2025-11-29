package com.globalcode.ges.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Location entity representing physical locations
 */
@Entity
@Table(name = "locations")
@NamedQueries({
    @NamedQuery(name = "Location.findByCity", 
                query = "SELECT l FROM Location l WHERE l.city = :city"),
    @NamedQuery(name = "Location.findByState", 
                query = "SELECT l FROM Location l WHERE l.state = :state")
})
public class Location extends BaseEntity {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "address", length = 200)
    private String address;
    
    @Column(name = "city", length = 50)
    private String city;
    
    @Column(name = "state", length = 50)
    private String state;
    
    @Column(name = "country", length = 50)
    private String country;
    
    @Column(name = "postal_code", length = 20)
    private String postalCode;
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassGroup> classGroups = new ArrayList<>();
    
    // Constructors
    public Location() {}
    
    public Location(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
    }
    
    // Getters and Setters
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
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public List<Member> getMembers() {
        return members;
    }
    
    public void setMembers(List<Member> members) {
        this.members = members;
    }
    
    public List<ClassGroup> getClassGroups() {
        return classGroups;
    }
    
    public void setClassGroups(List<ClassGroup> classGroups) {
        this.classGroups = classGroups;
    }
    
    @Override
    public String toString() {
        return "Location{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
