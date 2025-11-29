package com.globalcode.ges.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * ClassGroup entity representing course classes (equivalent to Turma)
 * Using modern Java 21 features and java.time API
 */
@Entity
@Table(name = "class_groups")
@NamedQueries({
    @NamedQuery(name = "ClassGroup.findByCourse", 
                query = "SELECT cg FROM ClassGroup cg WHERE cg.course = :course"),
    @NamedQuery(name = "ClassGroup.findByInstructor", 
                query = "SELECT cg FROM ClassGroup cg WHERE cg.instructor = :instructor"),
    @NamedQuery(name = "ClassGroup.findByStatus", 
                query = "SELECT cg FROM ClassGroup cg WHERE cg.status = :status"),
    @NamedQuery(name = "ClassGroup.findByDateRange", 
                query = "SELECT cg FROM ClassGroup cg WHERE cg.startDate >= :startDate AND cg.endDate <= :endDate")
})
public final class ClassGroup extends BaseEntity implements Auditable {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", length = 300)
    private String description;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "max_students")
    private Integer maxStudents;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    
    @OneToMany(mappedBy = "classGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();
    
    @OneToMany(mappedBy = "classGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassSession> sessions = new ArrayList<>();
    
    // Constructors
    public ClassGroup() {}
    
    public ClassGroup(String name, Course course, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
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
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public Integer getMaxStudents() {
        return maxStudents;
    }
    
    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    public Instructor getInstructor() {
        return instructor;
    }
    
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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
    
    public List<ClassSession> getSessions() {
        return sessions;
    }
    
    public void setSessions(List<ClassSession> sessions) {
        this.sessions = sessions;
    }
    
    public int getCurrentEnrollmentCount() {
        return enrollments != null ? enrollments.size() : 0;
    }
    
    public boolean isFullyBooked() {
        return maxStudents != null && getCurrentEnrollmentCount() >= maxStudents;
    }
    
    @Override
    public String toString() {
        return "ClassGroup{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }
}
