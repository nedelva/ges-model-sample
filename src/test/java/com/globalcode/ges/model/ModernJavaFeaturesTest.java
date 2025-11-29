package com.globalcode.ges.model;

import com.globalcode.ges.model.util.EntityFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests demonstrating modern Java 21 features:
 * - Sealed interfaces
 * - Switch expressions with pattern matching
 * - Text blocks
 * - Modern date/time API
 * - Records as embeddables
 */
@DisplayName("Modern Java 21 Features Tests")
class ModernJavaFeaturesTest {
    
    @Test
    @DisplayName("Should use LocalDateTime instead of Date")
    void testModernDateTimeAPI() {
        // Given
        Member member = new Member("Test User", "test@example.com");
        
        // When - timestamps are set automatically
        member.onCreate();
        
        // Then - using modern LocalDateTime
        assertThat(member.getCreatedDate())
            .isNotNull()
            .isInstanceOf(LocalDateTime.class)
            .isBeforeOrEqualTo(LocalDateTime.now());
            
        assertThat(member.getUpdatedDate())
            .isNotNull()
            .isInstanceOf(LocalDateTime.class);
    }
    
    @Test
    @DisplayName("Should use LocalDate for class group dates")
    void testLocalDateForClassGroups() {
        // Given
        Course course = new Course("Java", "JAVA-101", 40);
        LocalDate startDate = LocalDate.of(2025, 1, 15);
        LocalDate endDate = LocalDate.of(2025, 3, 15);
        
        // When
        ClassGroup classGroup = new ClassGroup("Spring 2025", course, startDate, endDate);
        
        // Then
        assertThat(classGroup.getStartDate())
            .isNotNull()
            .isInstanceOf(LocalDate.class)
            .isEqualTo(startDate);
            
        assertThat(classGroup.getEndDate())
            .isEqualTo(endDate);
    }
    
    @Test
    @DisplayName("Should implement sealed Auditable interface")
    void testSealedInterface() {
        // Given
        Member member = new Member("John", "john@example.com");
        member.onCreate();
        
        // When - using sealed interface
        Auditable auditable = member;
        
        // Then
        assertThat(auditable).isInstanceOf(Member.class);
        assertThat(auditable.getCreatedDate()).isNotNull();
        assertThat(auditable.isRecentlyCreated()).isTrue();
    }
    
    @Test
    @DisplayName("Should format entities using switch expressions")
    void testSwitchExpressions() {
        // Given
        Member member = new Member("Alice", "alice@example.com");
        Course course = new Course("Python", "PY-101", 30);
        
        // When - using switch expressions with pattern matching
        String memberFormat = EntityFormatter.formatEntity(member);
        String courseFormat = EntityFormatter.formatEntity(course);
        
        // Then
        assertThat(memberFormat)
            .contains("Member:", "Alice", "alice@example.com");
            
        assertThat(courseFormat)
            .contains("Course Information:", "Python", "PY-101", "30 hours");
    }
    
    @Test
    @DisplayName("Should use text blocks for multi-line formatting")
    void testTextBlocks() {
        // Given
        Course course = new Course("Spring Boot", "SPRING-101", 40);
        course.setStatus(Status.ACTIVE);
        
        // When - EntityFormatter uses text blocks internally
        String formatted = EntityFormatter.formatEntity(course);
        
        // Then - text block produces clean multi-line output
        assertThat(formatted)
            .contains("Course Information:")
            .contains("Name: Spring Boot")
            .contains("Code: SPRING-101")
            .contains("Duration: 40 hours")
            .contains("Status: ACTIVE");
    }
    
    @Test
    @DisplayName("Should use records for embeddable value objects")
    void testRecordsAsEmbeddables() {
        // Given - ContactInfo is a record
        ContactInfo contactInfo = new ContactInfo("user@test.com", "555-0123");
        
        // When
        Member member = new Member("Test", contactInfo);
        
        // Then - records provide immutability and built-in methods
        assertThat(member.getContactInfo())
            .isNotNull()
            .isEqualTo(contactInfo);
            
        // Records have automatic equals/hashCode
        ContactInfo sameContact = new ContactInfo("user@test.com", "555-0123");
        assertThat(contactInfo).isEqualTo(sameContact);
        
        // Records have automatic toString
        assertThat(contactInfo.toString())
            .contains("user@test.com", "555-0123");
    }
    
    @Test
    @DisplayName("Should use Address record for location embeddable")
    void testAddressRecord() {
        // Given - Address is a record
        Address address = new Address("123 Main St", "San Francisco", "CA", "USA", "94102");
        
        // When
        Location location = new Location("HQ Office", address);
        
        // Then
        assertThat(location.getAddress())
            .isNotNull()
            .isEqualTo(address);
            
        assertThat(address.city()).isEqualTo("San Francisco");
        assertThat(address.state()).isEqualTo("CA");
        assertThat(address.postalCode()).isEqualTo("94102");
    }
    
    @Test
    @DisplayName("Should get status descriptions using switch expressions")
    void testStatusSwitchExpression() {
        // When & Then - using switch expressions
        assertThat(EntityFormatter.getStatusDescription(Status.ACTIVE))
            .isEqualTo("Currently active and operational");
            
        assertThat(EntityFormatter.getStatusDescription(Status.INACTIVE))
            .isEqualTo("Temporarily inactive");
            
        assertThat(EntityFormatter.getStatusDescription(Status.PENDING))
            .isEqualTo("Awaiting approval or processing");
            
        assertThat(EntityFormatter.getStatusDescription(Status.CANCELLED))
            .isEqualTo("Permanently cancelled");
    }
    
    @Test
    @DisplayName("Should use pattern matching with instanceof")
    void testPatternMatching() {
        // Given
        Member member = new Member("Bob", "bob@example.com");
        
        // When - pattern matching extracts variable in one step
        if (member.getContactInfo() instanceof ContactInfo contact) {
            // Then - contact variable is available in this scope
            assertThat(contact.email()).isEqualTo("bob@example.com");
        } else {
            fail("ContactInfo should be present");
        }
    }
}
