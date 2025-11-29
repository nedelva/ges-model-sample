package com.globalcode.ges.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Member entity representing system users
 */
@Entity
@Table(name = "members")
@NamedQueries({
    @NamedQuery(name = "Member.findByEmail", 
                query = "SELECT m FROM Member m WHERE m.contactInfo.email = :email"),
    @NamedQuery(name = "Member.findByStatus", 
                query = "SELECT m FROM Member m WHERE m.status = :status"),
    @NamedQuery(name = "Member.findByName", 
                query = "SELECT m FROM Member m WHERE m.name LIKE :name ORDER BY m.name")
})
public class Member extends BaseEntity {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Embedded
    private ContactInfo contactInfo;
    
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
        this.contactInfo = new ContactInfo(email);
    }
    
    public Member(String name, ContactInfo contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public ContactInfo getContactInfo() {
        return contactInfo;
    }
    
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    // Convenience methods for backward compatibility
    public String getEmail() {
        return contactInfo != null ? contactInfo.email() : null;
    }
    
    public String getPhone() {
        return contactInfo != null ? contactInfo.phone() : null;
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
                ", contactInfo=" + contactInfo +
                ", status=" + status +
                '}';
    }
}
