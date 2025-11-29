package com.globalcode.ges.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

/**
 * ClassSession entity representing individual class sessions
 */
@Entity
@Table(name = "class_sessions")
@NamedQueries({
    @NamedQuery(name = "ClassSession.findByClassGroup", 
                query = "SELECT cs FROM ClassSession cs WHERE cs.classGroup = :classGroup ORDER BY cs.sessionDate"),
    @NamedQuery(name = "ClassSession.findByDate", 
                query = "SELECT cs FROM ClassSession cs WHERE cs.sessionDate = :date"),
    @NamedQuery(name = "ClassSession.findByDateRange", 
                query = "SELECT cs FROM ClassSession cs WHERE cs.sessionDate >= :startDate AND cs.sessionDate <= :endDate")
})
public class ClassSession extends BaseEntity {
    
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "session_date", nullable = false)
    private Date sessionDate;
    
    @Column(name = "duration_minutes")
    private Integer durationMinutes;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_group_id", nullable = false)
    private ClassGroup classGroup;
    
    @OneToMany(mappedBy = "classSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendances = new ArrayList<>();
    
    // Constructors
    public ClassSession() {}
    
    public ClassSession(String title, Date sessionDate, ClassGroup classGroup) {
        this.title = title;
        this.sessionDate = sessionDate;
        this.classGroup = classGroup;
    }
    
    // Getters and Setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getSessionDate() {
        return sessionDate;
    }
    
    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }
    
    public Integer getDurationMinutes() {
        return durationMinutes;
    }
    
    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public ClassGroup getClassGroup() {
        return classGroup;
    }
    
    public void setClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }
    
    public List<Attendance> getAttendances() {
        return attendances;
    }
    
    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
    
    @Override
    public String toString() {
        return "ClassSession{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", sessionDate=" + sessionDate +
                ", durationMinutes=" + durationMinutes +
                ", status=" + status +
                '}';
    }
}
