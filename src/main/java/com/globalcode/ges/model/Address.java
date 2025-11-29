package com.globalcode.ges.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Address embeddable record representing physical address information
 * Using Java records with JPA 3.1+ for immutable value objects
 */
@Embeddable
public record Address(
    @Column(name = "address", length = 200)
    String street,
    
    @Column(name = "city", length = 50)
    String city,
    
    @Column(name = "state", length = 50)
    String state,
    
    @Column(name = "country", length = 50)
    String country,
    
    @Column(name = "postal_code", length = 20)
    String postalCode
) {
    // Compact constructor for validation
    public Address {
        // Records automatically generate constructor, getters, equals, hashCode, toString
    }
    
    // Convenience constructor for partial address
    public Address(String street, String city, String state) {
        this(street, city, state, null, null);
    }
}
