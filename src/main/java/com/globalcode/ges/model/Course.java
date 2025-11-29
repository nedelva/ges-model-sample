package com.globalcode.ges.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Course entity representing educational courses
 */
@Entity
@Table(name = "courses")
// Using repeatable @NamedQuery annotations (JPA 3.1+ feature)
@NamedQuery(name = "Course.findByName", 
            query = "SELECT c FROM Course c WHERE c.name LIKE :name")
@NamedQuery(name = "Course.findByStatus", 
            query = "SELECT c FROM Course c WHERE c.status = :status")
@NamedQuery(name = "Course.findByDuration", 
            query = "SELECT c FROM Course c WHERE c.durationHours = :duration")
public final class Course extends BaseEntity implements Auditable {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "code", unique = true, length = 20)
    private String code;
    
    @Column(name = "duration_hours")
    private Integer durationHours;
    
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassGroup> classGroups = new ArrayList<>();
    
    // Constructors
    public Course() {}
    
    public Course(String name, String code, Integer durationHours) {
        this.name = name;
        this.code = code;
        this.durationHours = durationHours;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public Integer getDurationHours() {
        return durationHours;
    }
    
    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
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
        return "Course{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", durationHours=" + durationHours +
                ", status=" + status +
                '}';
    }
}
