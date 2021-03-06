package com.mayank.authentication.model;

import javax.persistence.Entity;
import javax.persistence.Id;

// three roles  -- admin -- teachers -- student
@Entity
public class Role {
    @Id
    private String role;
    private String description;
    
    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRole(String role) {
        this.role = role;

    }
}
