package com.mayank.authentication.model;

import javax.persistence.*;

@Entity
public class Score {
    @EmbeddedId
    private CompositeKeyForScore compositeKeyForScore;
    private Integer score;

    
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "courseid")
    private CourseName courseName;

    
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentid")
    private Student student;


    
    public CourseName getCourseName() {
        return courseName;
    }

    public void setCourseName(CourseName courseName) {
        this.courseName = courseName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CompositeKeyForScore getCompositeKeyForScore() {
        return compositeKeyForScore;
    }

    public void setCompositeKeyForScore(CompositeKeyForScore compositeKeyForScore) {
        this.compositeKeyForScore = compositeKeyForScore;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
