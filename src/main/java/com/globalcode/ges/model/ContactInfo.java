package com.globalcode.ges.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * ContactInfo embeddable record representing contact information
 * Using Java records with JPA 3.1+ for immutable value objects
 */
@Embeddable
public record ContactInfo(
    @Column(name = "email", nullable = false, unique = true, length = 100)
    String email,
    
    @Column(name = "phone", length = 20)
    String phone
) {
    // Compact constructor for validation
    public ContactInfo {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
    }
    
    // Convenience constructor with email only
    public ContactInfo(String email) {
        this(email, null);
    }
}
