package com.mayank.authentication.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class CourseName {
    @Id
    private String courseId;
    private String courseName;
    
    @OneToOne(targetEntity = Staff.class,mappedBy ="teaching")
    private Staff staff;
    
    @OneToMany( mappedBy = "courseName",cascade = CascadeType.ALL)
    private Set<Score> score;

    @ManyToOne
    @JoinColumn(name="Class",nullable=true)
    public Classes clas;


    
    public Set<Score> getScore() {
        return score;
    }

    public void setScore(Set<Score> score) {
        this.score = score;
    }

    public Classes getClas() {
        return clas;
    }

    public void setClas(Classes clas) {
        this.clas = clas;
    }


    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "CourseName [clas=" + clas + ", courseId=" + courseId + ", courseName=" + courseName + ", score=" + score
                + ", staff=" + staff + "]";
    }
    
}
