package com.mayank.authentication.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @Id
    private String studentId;
    private String studentName;
    private String studentRollNo;
    private String studentGender;
    private String studentDob;
    private String studentEmail;
    private String studentPhoneNo;
    private String studentAddress;
    private String studentPassword;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "Class",nullable = false)
    private Classes clas;

    // @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    // @JoinTable(name="Student_course",
    // joinColumns ={
    //         @JoinColumn(name="STUDENT_ID")
    // },
    //         inverseJoinColumns = {@JoinColumn(name="Course_Id")}
    // )
    // private Set<CourseName> courseNames;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private Set<Score> score;
    @ManyToOne
    @JoinColumn(name="role",nullable=false)
    private Role role;
    

   

    public String getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(String studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentDob(String studentDob) {
        this.studentDob = studentDob;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStudentId() {
        return studentId;
    }

    public Classes getClas() {
        return clas;
    }

    public void setClas(Classes clas) {
        this.clas = clas;
    }

    // public Set<CourseName> getCourseNames() {
    //     return courseNames;
    // }

    // public void setCourseNames(Set<CourseName> courseNames) {
    //     this.courseNames = courseNames;
    // }

    public Set<Score> getScore() {
        return score;
    }

    public void setScore(Set<Score> score) {
        this.score = score;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentDob() {
        return studentDob;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhoneNo() {
        return studentPhoneNo;
    }

    public void setStudentPhoneNo(String studentPhoneNo) {
        this.studentPhoneNo = studentPhoneNo;
    }

   

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    @Override
    public String toString() {
        return "Student [clas=" + clas + ", role=" + role + ", score=" + score + ", studentAddress=" + studentAddress
                + ", studentDob=" + studentDob + ", studentEmail=" + studentEmail + ", studentGender=" + studentGender
                + ", studentId=" + studentId + ", studentName=" + studentName + ", studentPassword=" + studentPassword
                + ", studentPhoneNo=" + studentPhoneNo + "]";
    }

  

   


}
