package com.globalcode.ges.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Simple test for Member entity
 */
public class MemberTest {
    
    @Test
    public void testMemberCreation() {
        Member member = new Member("John Doe", "john.doe@example.com");
        
        assertNotNull(member);
        assertEquals("John Doe", member.getName());
        assertEquals("john.doe@example.com", member.getEmail());
        assertEquals(Status.ACTIVE, member.getStatus());
        assertNotNull(member.getEnrollments());
        assertNotNull(member.getGroups());
    }
    
    @Test
    public void testMemberToString() {
        Member member = new Member("Jane Smith", "jane.smith@example.com");
        member.setId(1L);
        
        String result = member.toString();
        assertTrue(result.contains("Jane Smith"));
        assertTrue(result.contains("jane.smith@example.com"));
        assertTrue(result.contains("ACTIVE"));
    }
    
    @Test
    public void testMemberStatus() {
        Member member = new Member();
        assertEquals(Status.ACTIVE, member.getStatus());
        
        member.setStatus(Status.INACTIVE);
        assertEquals(Status.INACTIVE, member.getStatus());
    }
}
