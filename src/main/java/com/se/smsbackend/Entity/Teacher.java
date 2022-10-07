package com.se.smsbackend.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TeacherId;
    private String TeacherFirstName;
    private String TeacherLastName;
    private String TeacherFullName;
    private String TeacherEmail;
    private String TeacherTp;
    private String TeacherAddress;

    @ManyToMany  (mappedBy = "TeacherListForSubject")
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

    public String getTeacherFirstName() {
        return TeacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        TeacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return TeacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        TeacherLastName = teacherLastName;
    }

    public String getTeacherFullName() {
        return TeacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        TeacherFullName = teacherFullName;
    }

    public String getTeacherEmail() {
        return TeacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        TeacherEmail = teacherEmail;
    }

    public String getTeacherTp() {
        return TeacherTp;
    }

    public void setTeacherTp(String teacherTp) {
        TeacherTp = teacherTp;
    }

    public String getTeacherAddress() {
        return TeacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        TeacherAddress = teacherAddress;
    }
}
