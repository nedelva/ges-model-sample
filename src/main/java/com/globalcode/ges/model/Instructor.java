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
    
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "phone", length = 20)
    private String phone;
    
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
        this.email = email;
        this.specialty = specialty;
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
                ", email='" + email + '\'' +
                ", specialty='" + specialty + '\'' +
                ", status=" + status +
                '}';
    }
}
