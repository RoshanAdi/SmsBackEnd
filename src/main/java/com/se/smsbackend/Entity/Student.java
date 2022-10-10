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
    private String name;  //full name
    private String birthDate;
    private String Age;
    private String Email;
    private String Tp;
    private String Address;
    private String StudentPassword;

    @Column(name = "verification_code", length = 64)
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

    public String getStudentPassword() {
        return StudentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        StudentPassword = studentPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    @Override
    public String toString() {
        return "Student{" +
                "StudentId=" + StudentId +
                ", subjectList=" + subjectList +
                ", AttendanceList=" + AttendanceList +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", Age='" + Age + '\'' +
                ", Email='" + Email + '\'' +
                ", Tp='" + Tp + '\'' +
                ", Address='" + Address + '\'' +
                ", StudentPassword='" + StudentPassword + '\'' +
                '}';
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
}

