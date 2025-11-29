package com.globalcode.ges.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Group entity representing member groups
 */
@Entity
@Table(name = "groups")
@NamedQueries({
    @NamedQuery(name = "Group.findByName", 
                query = "SELECT g FROM Group g WHERE g.name LIKE :name"),
    @NamedQuery(name = "Group.findByType", 
                query = "SELECT g FROM Group g WHERE g.type = :type")
})
public class Group extends BaseEntity {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", length = 300)
    private String description;
    
    @Column(name = "type", length = 50)
    private String type;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "group_members",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members = new ArrayList<>();
    
    // Constructors
    public Group() {}
    
    public Group(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public List<Member> getMembers() {
        return members;
    }
    
    public void setMembers(List<Member> members) {
        this.members = members;
    }
    
    public void addMember(Member member) {
        if (!members.contains(member)) {
            members.add(member);
            member.getGroups().add(this);
        }
    }
    
    public void removeMember(Member member) {
        if (members.contains(member)) {
            members.remove(member);
            member.getGroups().remove(this);
        }
    }
    
    @Override
    public String toString() {
        return "Group{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", memberCount=" + (members != null ? members.size() : 0) +
                '}';
    }
}
