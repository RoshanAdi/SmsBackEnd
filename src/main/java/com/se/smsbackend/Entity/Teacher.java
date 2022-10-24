package com.se.smsbackend.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TeacherId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private String tp;
    private String address;
    private String nIc;
    private  String password;
    private  String role;
    private  boolean enabled;
    private  String verificationCode;



    @ManyToMany(mappedBy = "TeacherListForSubject")
    private List<Subject> subjectListForTeacher = new ArrayList<>();

    public List<Subject> getSubjectListForTeacher() {
        return subjectListForTeacher;
    }

    public void setSubjectListForTeacher(List<Subject> subjectListForTeacher) {
        this.subjectListForTeacher = subjectListForTeacher;
    }


    public int getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(int teacherId) {
        TeacherId = teacherId;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getnIc() {
        return nIc;
    }

    public void setnIc(String nIc) {
        this.nIc = nIc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "TeacherId=" + TeacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", tp='" + tp + '\'' +
                ", address='" + address + '\'' +
                ", nIc='" + nIc + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                ", verificationCode='" + verificationCode + '\'' +
                ", subjectListForTeacher=" + subjectListForTeacher +
                '}';
    }
}



