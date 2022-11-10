package com.se.smsbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "assignmentQuestions", cascade = CascadeType.ALL,orphanRemoval = true , fetch = FetchType.LAZY)
    private List<McqQuestion> mcqList = new ArrayList<>();

    @OneToMany(mappedBy = "assignmentOfFile", cascade = CascadeType.ALL,orphanRemoval = true , fetch = FetchType.LAZY)
    private List<FileDB> fileDBList = new ArrayList<>();


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

    public List<McqQuestion> getMcqList() {
        return mcqList;
    }

    public void setMcqList(List<McqQuestion> mcqList) {
        this.mcqList = mcqList;
    }

    public List<FileDB> getFileDBList() {
        return fileDBList;
    }

    public void setFileDBList(List<FileDB> fileDBList) {
        this.fileDBList = fileDBList;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assigmentID=" + assigmentID +
                ", assigmentTitle='" + assigmentTitle + '\'' +
                ", assigmentDiscription='" + assigmentDiscription + '\'' +
                ", assigmentData='" + assigmentData + '\'' +
                ", subjectForAssignment=" + subjectForAssignment +
                ", mcqList=" + mcqList +
                '}';
    }
}


