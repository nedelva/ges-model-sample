package com.globalcode.ges.model;

import java.time.LocalDateTime;

/**
 * Sealed interface demonstrating Java 17+ sealed types feature
 * Restricts which classes can implement this interface
 * 
 * This provides compile-time guarantees about the type hierarchy
 * and enables exhaustive pattern matching in switch expressions
 */
public sealed interface Auditable permits Member, Course, ClassGroup, Instructor, Location {
    
    LocalDateTime getCreatedDate();
    LocalDateTime getUpdatedDate();
    
    /**
     * Check if entity was recently created (within last 24 hours)
     */
    default boolean isRecentlyCreated() {
        if (getCreatedDate() == null) {
            return false;
        }
        return getCreatedDate().isAfter(LocalDateTime.now().minusDays(1));
    }
    
    /**
     * Check if entity was recently updated (within last 24 hours)
     */
    default boolean isRecentlyUpdated() {
        if (getUpdatedDate() == null) {
            return false;
        }
        return getUpdatedDate().isAfter(LocalDateTime.now().minusDays(1));
    }
}
