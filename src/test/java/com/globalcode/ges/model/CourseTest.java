package com.globalcode.ges.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for Course entity demonstrating modern testing features
 */
@DisplayName("Course Entity Tests")
class CourseTest {
    
    private Course course;
    
    @BeforeEach
    void setUp() {
        course = new Course("Java Programming", "JAVA-101", 40);
    }
    
    @Test
    @DisplayName("Should create course with required fields")
    void testCourseCreation() {
        // Then
        assertAll("Course creation",
            () -> assertNotNull(course),
            () -> assertEquals("Java Programming", course.getName()),
            () -> assertEquals("JAVA-101", course.getCode()),
            () -> assertEquals(40, course.getDurationHours()),
            () -> assertEquals(Status.ACTIVE, course.getStatus())
        );
        
        // Using AssertJ
        assertThat(course)
            .extracting(Course::getName, Course::getCode, Course::getDurationHours)
            .containsExactly("Java Programming", "JAVA-101", 40);
    }
    
    @Test
    @DisplayName("Should set and get course price")
    void testCoursePrice() {
        // Given
        BigDecimal price = new BigDecimal("999.99");
        
        // When
        course.setPrice(price);
        
        // Then
        assertThat(course.getPrice())
            .isNotNull()
            .isEqualByComparingTo(price);
    }
    
    @ParameterizedTest(name = "Duration {0} hours should be valid")
    @ValueSource(ints = {8, 16, 24, 40, 80, 120})
    @DisplayName("Should accept various valid durations")
    void testValidDurations(int duration) {
        // When
        course.setDurationHours(duration);
        
        // Then
        assertThat(course.getDurationHours()).isEqualTo(duration);
    }
    
    @ParameterizedTest(name = "Course: {0}, Code: {1}, Duration: {2}h")
    @CsvSource({
        "Java Basics, JAVA-001, 20",
        "Advanced Java, JAVA-201, 40",
        "Spring Boot, SPRING-101, 32",
        "Microservices, MICRO-301, 48"
    })
    @DisplayName("Should create courses with different parameters")
    void testMultipleCourseCreations(String name, String code, int duration) {
        // When
        Course newCourse = new Course(name, code, duration);
        
        // Then
        assertThat(newCourse)
            .isNotNull()
            .satisfies(c -> {
                assertThat(c.getName()).isEqualTo(name);
                assertThat(c.getCode()).isEqualTo(code);
                assertThat(c.getDurationHours()).isEqualTo(duration);
                assertThat(c.getStatus()).isEqualTo(Status.ACTIVE);
            });
    }
    
    @Test
    @DisplayName("Should initialize empty class groups list")
    void testClassGroupsInitialization() {
        // Then
        assertThat(course.getClassGroups())
            .isNotNull()
            .isEmpty();
    }
    
    @Test
    @DisplayName("Should include course details in toString")
    void testToString() {
        // When
        String result = course.toString();
        
        // Then
        assertThat(result)
            .contains("Java Programming", "JAVA-101", "40", "ACTIVE");
    }
}
