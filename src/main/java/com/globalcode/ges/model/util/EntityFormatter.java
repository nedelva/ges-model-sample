package com.globalcode.ges.model.util;

import com.globalcode.ges.model.*;

import java.time.format.DateTimeFormatter;

/**
 * Utility class demonstrating modern Java 21 features:
 * - Switch expressions
 * - Pattern matching with instanceof
 * - Text blocks
 */
public class EntityFormatter {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    /**
     * Format entity using switch expressions (Java 14+, enhanced in Java 21)
     */
    public static String formatEntity(BaseEntity entity) {
        return switch (entity) {
            case Member m -> formatMember(m);
            case Course c -> formatCourse(c);
            case ClassGroup cg -> formatClassGroup(cg);
            case Instructor i -> formatInstructor(i);
            case Location l -> formatLocation(l);
            case null -> "null entity";
            default -> "Unknown entity type: " + entity.getClass().getSimpleName();
        };
    }
    
    /**
     * Get status description using switch expression
     */
    public static String getStatusDescription(Status status) {
        return switch (status) {
            case ACTIVE -> "Currently active and operational";
            case INACTIVE -> "Temporarily inactive";
            case PENDING -> "Awaiting approval or processing";
            case CANCELLED -> "Permanently cancelled";
        };
    }
    
    /**
     * Format member with pattern matching
     */
    private static String formatMember(Member member) {
        var info = new StringBuilder();
        info.append("Member: ").append(member.getName());
        
        if (member.getContactInfo() instanceof ContactInfo contact) {
            info.append(" (").append(contact.email()).append(")");
        }
        
        info.append(" - Status: ").append(member.getStatus());
        return info.toString();
    }
    
    /**
     * Format course using text blocks (Java 15+)
     */
    private static String formatCourse(Course course) {
        return """
                Course Information:
                  Name: %s
                  Code: %s
                  Duration: %d hours
                  Status: %s
                """.formatted(
                    course.getName(),
                    course.getCode(),
                    course.getDurationHours(),
                    course.getStatus()
                );
    }
    
    /**
     * Format class group with modern date formatting
     */
    private static String formatClassGroup(ClassGroup classGroup) {
        var startDate = classGroup.getStartDate() != null 
            ? classGroup.getStartDate().format(DATE_FORMATTER) 
            : "TBD";
        var endDate = classGroup.getEndDate() != null 
            ? classGroup.getEndDate().format(DATE_FORMATTER) 
            : "TBD";
            
        return """
                Class Group: %s
                  Period: %s to %s
                  Status: %s
                  Capacity: %d/%d
                """.formatted(
                    classGroup.getName(),
                    startDate,
                    endDate,
                    classGroup.getStatus(),
                    classGroup.getCurrentEnrollmentCount(),
                    classGroup.getMaxStudents() != null ? classGroup.getMaxStudents() : 0
                );
    }
    
    /**
     * Format instructor
     */
    private static String formatInstructor(Instructor instructor) {
        return "Instructor: %s - Specialty: %s".formatted(
            instructor.getName(),
            instructor.getSpecialty() != null ? instructor.getSpecialty() : "General"
        );
    }
    
    /**
     * Format location with address embeddable
     */
    private static String formatLocation(Location location) {
        var address = location.getAddress();
        if (address != null) {
            return "Location: %s - %s, %s".formatted(
                location.getName(),
                address.city() != null ? address.city() : "Unknown",
                address.state() != null ? address.state() : "Unknown"
            );
        }
        return "Location: " + location.getName();
    }
}
