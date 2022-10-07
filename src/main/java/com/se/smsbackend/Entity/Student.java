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



    private String StudentFirstName;
    private String StudentLastName;
    private String StudentFullName;
    private String StudentDOB;
    private String StudentAge;
    private String StudentEmail;
    private String StudentTp;
    private String StudentAddress;


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



    public String getStudentFirstName() {
        return StudentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        StudentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return StudentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        StudentLastName = studentLastName;
    }

    public String getStudentFullName() {
        return StudentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        StudentFullName = studentFullName;
    }

    public String getStudentDOB() {
        return StudentDOB;
    }

    public void setStudentDOB(String studentDOB) {
        StudentDOB = studentDOB;
    }

    public String getStudentAge() {
        return StudentAge;
    }

    public void setStudentAge(String studentAge) {
        StudentAge = studentAge;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }

    public String getStudentTp() {
        return StudentTp;
    }

    public void setStudentTp(String studentTp) {
        StudentTp = studentTp;
    }

    public String getStudentAddress() {
        return StudentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        StudentAddress = studentAddress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentId=" + StudentId +
                ", subjectList=" + subjectList +
                ", StudentFirstName='" + StudentFirstName + '\'' +
                ", StudentLastName='" + StudentLastName + '\'' +
                ", StudentFullName='" + StudentFullName + '\'' +
                ", StudentDOB='" + StudentDOB + '\'' +
                ", StudentAge='" + StudentAge + '\'' +
                ", StudentEmail='" + StudentEmail + '\'' +
                ", StudentTp='" + StudentTp + '\'' +
                ", StudentAddress='" + StudentAddress + '\'' +
                '}';
    }
}
