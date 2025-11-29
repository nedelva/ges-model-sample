package com.globalcode.ges.model;

/**
 * Status enumeration for entities
 */
public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    PENDING("Pending"),
    CANCELLED("Cancelled");
    
    private final String description;
    
    Status(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
