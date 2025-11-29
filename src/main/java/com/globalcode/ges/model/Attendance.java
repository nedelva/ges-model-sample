package com.globalcode.ges.model;

import java.util.Date;
import javax.persistence.*;

/**
 * Attendance entity representing student attendance in class sessions
 */
@Entity
@Table(name = "attendances")
@NamedQueries({
    @NamedQuery(name = "Attendance.findByMember", 
                query = "SELECT a FROM Attendance a WHERE a.member = :member"),
    @NamedQuery(name = "Attendance.findByClassSession", 
                query = "SELECT a FROM Attendance a WHERE a.classSession = :classSession"),
    @NamedQuery(name = "Attendance.findByMemberAndSession", 
                query = "SELECT a FROM Attendance a WHERE a.member = :member AND a.classSession = :classSession")
})
public class Attendance extends BaseEntity {
    
    @Column(name = "present", nullable = false)
    private Boolean present = false;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_in_time")
    private Date checkInTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_out_time")
    private Date checkOutTime;
    
    @Column(name = "notes", length = 300)
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_session_id", nullable = false)
    private ClassSession classSession;
    
    // Constructors
    public Attendance() {}
    
    public Attendance(Member member, ClassSession classSession, Boolean present) {
        this.member = member;
        this.classSession = classSession;
        this.present = present;
        if (present) {
            this.checkInTime = new Date();
        }
    }
    
    // Getters and Setters
    public Boolean getPresent() {
        return present;
    }
    
    public void setPresent(Boolean present) {
        this.present = present;
    }
    
    public Date getCheckInTime() {
        return checkInTime;
    }
    
    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }
    
    public Date getCheckOutTime() {
        return checkOutTime;
    }
    
    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Member getMember() {
        return member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    
    public ClassSession getClassSession() {
        return classSession;
    }
    
    public void setClassSession(ClassSession classSession) {
        this.classSession = classSession;
    }
    
    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + getId() +
                ", present=" + present +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                '}';
    }
}
