package com.se.smsbackend.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SubjectId;
    private String SubjectName;
    @ManyToMany
    private List<Student> StudentList = new ArrayList<>();

    @ManyToMany
    private List<Teacher> TeacherListForSubject = new ArrayList<>();

    @OneToMany(mappedBy = "subjectForAssignment")
    private List<Assignment> AssigmentList = new ArrayList<>();

    @OneToMany(mappedBy = "subjectForMark")
    private List<Marks> MarksList = new ArrayList<>();



    public List<Marks> getMarksList() {
        return MarksList;
    }

    public void setMarksList(List<Marks> marksList) {
        MarksList = marksList;
    }

    public List<Assignment> getAssigmentList() {
        return AssigmentList;
    }

    public void setAssigmentList(List<Assignment> assigmentList) {
        AssigmentList = assigmentList;
    }

    public List<Teacher> getTeacherListForSubject() {
        return TeacherListForSubject;
    }

    public void setTeacherListForSubject(List<Teacher> teacherListForSubject) {
        TeacherListForSubject = teacherListForSubject;
    }

    public List<Student> getStudentList() {
        return StudentList;
    }

    public void setStudentList(List<Student> studentList) {
        StudentList = studentList;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int subjectId) {
        SubjectId = subjectId;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
}
