package com.se.smsbackend.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TeacherId;
    private String FirstName;
    private String LastName;
    private String name;
    private String Email;
    private String Tp;
    private String Address;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Teacher{" +
                "TeacherId=" + TeacherId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", name='" + name + '\'' +
                ", Email='" + Email + '\'' +
                ", Tp='" + Tp + '\'' +
                ", Address='" + Address + '\'' +
                ", subjectListForTeacher=" + subjectListForTeacher +
                '}';
    }
}