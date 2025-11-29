package com.globalcode.ges.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Member entity representing system users
 */
@Entity
@Table(name = "members")
@NamedQueries({
    @NamedQuery(name = "Member.findByEmail", 
                query = "SELECT m FROM Member m WHERE m.email = :email"),
    @NamedQuery(name = "Member.findByStatus", 
                query = "SELECT m FROM Member m WHERE m.status = :status"),
    @NamedQuery(name = "Member.findByName", 
                query = "SELECT m FROM Member m WHERE m.name LIKE :name ORDER BY m.name")
})
public class Member extends BaseEntity {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @Column(name = "company", length = 100)
    private String company;
    
    @Column(name = "position", length = 100)
    private String position;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();
    
    @ManyToMany(mappedBy = "members")
    private List<Group> groups = new ArrayList<>();
    
    // Constructors
    public Member() {}
    
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
    
    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
    
    public List<Group> getGroups() {
        return groups;
    }
    
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
    @Override
    public String toString() {
        return "Member{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
