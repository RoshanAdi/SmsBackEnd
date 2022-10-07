package com.se.smsbackend.Entity;

import javax.persistence.*;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AssigmentID;
    private String AssigmentTitle;
    private String AssigmentDiscription;
    private String AssigmentData;      // change the type

    @ManyToOne
    private Subject subjectForAssignment;

    public Subject getSubjectForAssignment() {
        return subjectForAssignment;
    }

    public void setSubjectForAssignment(Subject subjectForAssignment) {
        this.subjectForAssignment = subjectForAssignment;
    }

    public int getAssigmentID() {
        return AssigmentID;
    }

    public void setAssigmentID(int assigmentID) {
        AssigmentID = assigmentID;
    }

    public String getAssigmentTitle() {
        return AssigmentTitle;
    }

    public void setAssigmentTitle(String assigmentTitle) {
        AssigmentTitle = assigmentTitle;
    }

    public String getAssigmentDiscription() {
        return AssigmentDiscription;
    }

    public void setAssigmentDiscription(String assigmentDiscription) {
        AssigmentDiscription = assigmentDiscription;
    }

    public String getAssigmentData() {
        return AssigmentData;
    }

    public void setAssigmentData(String assigmentData) {
        AssigmentData = assigmentData;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "AssigmentID=" + AssigmentID +
                ", AssigmentTitle='" + AssigmentTitle + '\'' +
                ", AssigmentDiscription='" + AssigmentDiscription + '\'' +
                ", AssigmentData='" + AssigmentData + '\'' +
                '}';
    }
}


