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
                query = "SELECT l FROM Location l WHERE l.address.city = :city"),
    @NamedQuery(name = "Location.findByState", 
                query = "SELECT l FROM Location l WHERE l.address.state = :state")
})
public final class Location extends BaseEntity implements Auditable {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Embedded
    private Address address;
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassGroup> classGroups = new ArrayList<>();
    
    // Constructors
    public Location() {}
    
    public Location(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    
    public Location(String name, String city, String state) {
        this.name = name;
        this.address = new Address(null, city, state, null, null);
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
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
                ", address=" + address +
                '}';
    }
}
