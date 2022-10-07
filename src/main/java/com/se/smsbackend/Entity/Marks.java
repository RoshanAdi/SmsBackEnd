package com.se.smsbackend.Entity;

import javax.persistence.*;

@Entity
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MarkId;
    private String StudentId;    //not sure the type here
    private String SubjectId;   //not sure whether these will need or not!
    private String Marks;

    @ManyToOne
    private Subject subjectForMark;    //may be need to connect with student too

    public Subject getSubjectForMark() {
        return subjectForMark;
    }

    public void setSubjectForMark(Subject subjectForMark) {
        this.subjectForMark = subjectForMark;
    }

    public int getMarkId() {
        return MarkId;
    }

    public void setMarkId(int markId) {
        MarkId = markId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String subjectId) {
        SubjectId = subjectId;
    }

    public String getMarks() {
        return Marks;
    }

    public void setMarks(String marks) {
        Marks = marks;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "MarkId=" + MarkId +
                ", StudentId='" + StudentId + '\'' +
                ", SubjectId='" + SubjectId + '\'' +
                ", Marks='" + Marks + '\'' +
                '}';
    }
}
