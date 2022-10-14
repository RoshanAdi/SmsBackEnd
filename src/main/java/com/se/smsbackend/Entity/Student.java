package com.se.smsbackend.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int StudentId;
    @ManyToMany(mappedBy="StudentList")
    private List<Subject> subjectList = new ArrayList<Subject>();

    @OneToMany(mappedBy = "studentForAttendance")
    private List<Attendance> AttendanceList = new ArrayList<>();



    private String FirstName;
    private String LastName;
    private String FullName;
    private String birthDate;
    private String Age;
    private String username;      ///username stands for email
    private String Tp;
    private String Address;
    private String password;

    private String Role;

    @Column(columnDefinition = "TEXT", length = 255)
    @Lob
    private String verificationCode;

    private boolean enabled;



    public List<Attendance> getAttendanceList() {
        return AttendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        AttendanceList = attendanceList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTp() {
        return Tp;
    }

    public void setTp(String tp) {
        Tp = tp;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }




    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentId=" + StudentId +
                ", subjectList=" + subjectList +
                ", AttendanceList=" + AttendanceList +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", FullName='" + FullName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", Age='" + Age + '\'' +
                ", username='" + username + '\'' +
                ", Tp='" + Tp + '\'' +
                ", Address='" + Address + '\'' +
                ", password='" + password + '\'' +
                ", Role='" + Role + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

