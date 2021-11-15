package com.mayank.authentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
// import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Staff {

    @Id
    @NotBlank(message="You cant leave it blank")
    private String jobId;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String designation;
    private String gender;
    private String dob;
    private String email;
    private String phoneNo;
    private String landlineNo;
    private String state;
    private String pin;
    private String previousJobDetail;
    private String address;
    private String district;
    

    @OneToOne(targetEntity = CourseName.class,cascade = CascadeType.ALL)
    private CourseName teaching;


    @ManyToOne
    @JoinColumn(name="role")
    private Role role;
    public String getDistrict() {
        return district;
    }
    
   

  

    public CourseName getTeaching() {
        return teaching;
    }





    public void setTeaching(CourseName teaching) {
        this.teaching = teaching;
    }





    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLandlineNo() {
        return landlineNo;
    }

    public void setLandlineNo(String landlineNo) {
        this.landlineNo = landlineNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPreviousJobDetail() {
        return previousJobDetail;
    }

    public void setPreviousJobDetail(String previousJobDetail) {
        this.previousJobDetail = previousJobDetail;
    }

    

    public String getMiddleName() {
        return middleName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }





    @Override
    public String toString() {
        return "Staff [address=" + address + ", designation=" + designation + ", district=" + district + ", dob=" + dob
                + ", email=" + email + ", firstName=" + firstName + ", gender=" + gender + ", jobId=" + jobId
                + ", landlineNo=" + landlineNo + ", lastName=" + lastName + ", middleName=" + middleName + ", password="
                + password + ", phoneNo=" + phoneNo + ", pin=" + pin + ", previousJobDetail=" + previousJobDetail
                + ", role=" + role + ", state=" + state + ", teaching=" + teaching + "]";
    }

}
