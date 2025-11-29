package com.globalcode.ges.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * Enrollment entity representing student enrollments in class groups
 */
@Entity
@Table(name = "enrollments")
@NamedQueries({
    @NamedQuery(name = "Enrollment.findByMember", 
                query = "SELECT e FROM Enrollment e WHERE e.member = :member"),
    @NamedQuery(name = "Enrollment.findByClassGroup", 
                query = "SELECT e FROM Enrollment e WHERE e.classGroup = :classGroup"),
    @NamedQuery(name = "Enrollment.findByStatus", 
                query = "SELECT e FROM Enrollment e WHERE e.status = :status"),
    @NamedQuery(name = "Enrollment.findByDateRange", 
                query = "SELECT e FROM Enrollment e WHERE e.enrollmentDate >= :startDate AND e.enrollmentDate <= :endDate")
})
public class Enrollment extends BaseEntity {
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enrollment_date", nullable = false)
    private Date enrollmentDate;
    
    @Column(name = "amount_paid", precision = 10, scale = 2)
    private BigDecimal amountPaid;
    
    @Column(name = "payment_method", length = 50)
    private String paymentMethod;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;
    
    @Column(name = "notes", length = 500)
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_group_id", nullable = false)
    private ClassGroup classGroup;
    
    // Constructors
    public Enrollment() {
        this.enrollmentDate = new Date();
    }
    
    public Enrollment(Member member, ClassGroup classGroup) {
        this();
        this.member = member;
        this.classGroup = classGroup;
    }
    
    // Getters and Setters
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }
    
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
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
    
    public ClassGroup getClassGroup() {
        return classGroup;
    }
    
    public void setClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }
    
    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + getId() +
                ", enrollmentDate=" + enrollmentDate +
                ", amountPaid=" + amountPaid +
                ", status=" + status +
                '}';
    }
}
