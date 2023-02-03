package com.se.smsbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class SubjectMarks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int studRank;
    private String marksUpdateId;
    private String marks;
    private String studentUsername;
    private String subjectId;
    private String studentName;
    private String marksOutOf;


    @ManyToOne
    @JoinColumn(name = "Student", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "Subject", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Subject subject;

    public int getStudRank() {
        return studRank;
    }

    public void setStudRank(int studRank) {
        this.studRank = studRank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarksUpdateId() {
        return marksUpdateId;
    }

    public void setMarksUpdateId(String marksUpdateId) {
        this.marksUpdateId = marksUpdateId;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getMarksOutOf() {
        return marksOutOf;
    }

    public void setMarksOutOf(String marksOutOf) {
        this.marksOutOf = marksOutOf;
    }


}
