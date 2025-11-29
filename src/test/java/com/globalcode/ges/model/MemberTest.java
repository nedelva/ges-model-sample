package com.globalcode.ges.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for Member entity with modern assertions
 */
@DisplayName("Member Entity Tests")
class MemberTest {
    
    @Test
    @DisplayName("Should create member with name and email")
    void testMemberCreation() {
        // Given
        String name = "John Doe";
        String email = "john.doe@example.com";
        
        // When
        Member member = new Member(name, email);
        
        // Then - Using JUnit 5 assertions
        assertAll("Member creation",
            () -> assertNotNull(member, "Member should not be null"),
            () -> assertEquals(name, member.getName(), "Name should match"),
            () -> assertEquals(email, member.getEmail(), "Email should match"),
            () -> assertEquals(Status.ACTIVE, member.getStatus(), "Default status should be ACTIVE"),
            () -> assertNotNull(member.getEnrollments(), "Enrollments list should not be null"),
            () -> assertNotNull(member.getGroups(), "Groups list should not be null")
        );
        
        // Then - Using AssertJ fluent assertions
        assertThat(member)
            .isNotNull()
            .extracting(Member::getName, Member::getEmail, Member::getStatus)
            .containsExactly(name, email, Status.ACTIVE);
            
        assertThat(member.getEnrollments()).isEmpty();
        assertThat(member.getGroups()).isEmpty();
    }
    
    @Test
    @DisplayName("Should include member details in toString")
    void testMemberToString() {
        // Given
        Member member = new Member("Jane Smith", "jane.smith@example.com");
        member.setId(1L);
        
        // When
        String result = member.toString();
        
        // Then - Using JUnit 5 assertions
        assertAll("toString content",
            () -> assertTrue(result.contains("Jane Smith"), "Should contain name"),
            () -> assertTrue(result.contains("jane.smith@example.com"), "Should contain email"),
            () -> assertTrue(result.contains("ACTIVE"), "Should contain status")
        );
        
        // Then - Using AssertJ fluent assertions
        assertThat(result)
            .contains("Jane Smith", "jane.smith@example.com", "ACTIVE")
            .contains("id=1");
    }
    
    @Nested
    @DisplayName("Status Management Tests")
    class StatusTests {
        
        @Test
        @DisplayName("Should have ACTIVE status by default")
        void testDefaultStatus() {
            // Given & When
            Member member = new Member();
            
            // Then
            assertThat(member.getStatus())
                .isNotNull()
                .isEqualTo(Status.ACTIVE);
        }
        
        @Test
        @DisplayName("Should allow status change")
        void testStatusChange() {
            // Given
            Member member = new Member();
            assertEquals(Status.ACTIVE, member.getStatus());
            
            // When
            member.setStatus(Status.INACTIVE);
            
            // Then
            assertThat(member.getStatus()).isEqualTo(Status.INACTIVE);
        }
    }
    
    @Nested
    @DisplayName("ContactInfo Embeddable Tests")
    class ContactInfoTests {
        
        @Test
        @DisplayName("Should create member with ContactInfo embeddable")
        void testContactInfoEmbeddable() {
            // Given
            ContactInfo contactInfo = new ContactInfo("test@example.com", "555-1234");
            
            // When
            Member member = new Member("Test User", contactInfo);
            
            // Then
            assertThat(member.getContactInfo())
                .isNotNull()
                .isEqualTo(contactInfo);
                
            assertThat(member.getEmail()).isEqualTo("test@example.com");
            assertThat(member.getPhone()).isEqualTo("555-1234");
        }
        
        @Test
        @DisplayName("Should handle null phone in ContactInfo")
        void testContactInfoWithNullPhone() {
            // Given
            ContactInfo contactInfo = new ContactInfo("user@example.com");
            
            // When
            Member member = new Member("User", contactInfo);
            
            // Then
            assertThat(member.getEmail()).isEqualTo("user@example.com");
            assertThat(member.getPhone()).isNull();
        }
    }
}
