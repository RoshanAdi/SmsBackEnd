package com.se.smsbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assigmentID;
    private String assigmentTitle;
    private String assigmentDiscription;
    private String assigmentData;      // change the type

    @ManyToOne
    @JoinColumn(name = "subject4assign", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Subject subjectForAssignment;




    public Subject getSubjectForAssignment() {
        return subjectForAssignment;
    }

    public void setSubjectForAssignment(Subject subjectForAssignment) {
        this.subjectForAssignment = subjectForAssignment;
    }

    public int getAssigmentID() {
        return assigmentID;
    }

    public void setAssigmentID(int assigmentID) {
        this.assigmentID = assigmentID;
    }

    public String getAssigmentTitle() {
        return assigmentTitle;
    }

    public void setAssigmentTitle(String assigmentTitle) {
        this.assigmentTitle = assigmentTitle;
    }

    public String getAssigmentDiscription() {
        return assigmentDiscription;
    }

    public void setAssigmentDiscription(String assigmentDiscription) {
        this.assigmentDiscription = assigmentDiscription;
    }

    public String getAssigmentData() {
        return assigmentData;
    }

    public void setAssigmentData(String assigmentData) {
        this.assigmentData = assigmentData;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assigmentID=" + assigmentID +
                ", assigmentTitle='" + assigmentTitle + '\'' +
                ", assigmentDiscription='" + assigmentDiscription + '\'' +
                ", assigmentData='" + assigmentData + '\'' +
                ", subjectForAssignment=" + subjectForAssignment +
                '}';
    }
}


