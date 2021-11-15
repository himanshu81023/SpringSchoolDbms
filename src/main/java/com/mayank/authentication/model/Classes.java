package com.mayank.authentication.model;

// import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Embeddable
@Entity
public class Classes {
    @Id
    private String clas;
    @OneToMany(cascade =CascadeType.ALL, mappedBy = "clas")
    public Set<CourseName> courseNames;

    public Set<CourseName> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(Set<CourseName> courseNames) {
        this.courseNames = courseNames;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }



}
