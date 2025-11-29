package com.globalcode.ges.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Instructor entity representing course instructors
 */
@Entity
@Table(name = "instructors")
@NamedQueries({
    @NamedQuery(name = "Instructor.findByName", 
                query = "SELECT i FROM Instructor i WHERE i.name LIKE :name"),
    @NamedQuery(name = "Instructor.findBySpecialty", 
                query = "SELECT i FROM Instructor i WHERE i.specialty LIKE :specialty"),
    @NamedQuery(name = "Instructor.findByStatus", 
                query = "SELECT i FROM Instructor i WHERE i.status = :status")
})
public class Instructor extends BaseEntity {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Embedded
    private ContactInfo contactInfo;
    
    @Column(name = "specialty", length = 100)
    private String specialty;
    
    @Column(name = "bio", length = 1000)
    private String bio;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;
    
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassGroup> classGroups = new ArrayList<>();
    
    // Constructors
    public Instructor() {}
    
    public Instructor(String name, String email, String specialty) {
        this.name = name;
        this.contactInfo = new ContactInfo(email);
        this.specialty = specialty;
    }
    
    public Instructor(String name, ContactInfo contactInfo, String specialty) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.specialty = specialty;
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
    
    public String getSpecialty() {
        return specialty;
    }
    
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public List<ClassGroup> getClassGroups() {
        return classGroups;
    }
    
    public void setClassGroups(List<ClassGroup> classGroups) {
        this.classGroups = classGroups;
    }
    
    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", contactInfo=" + contactInfo +
                ", specialty='" + specialty + '\'' +
                ", status=" + status +
                '}';
    }
}
