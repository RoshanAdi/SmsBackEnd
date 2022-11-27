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
    private String assigmentData;
    private String startTime;
    private String endTime;
    private int noOfAttempts;

    @OneToMany(mappedBy = "assignment",cascade = CascadeType.ALL ,orphanRemoval = true  ,fetch = FetchType.LAZY)
    private List<Marks> marksSet = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "subject4assign", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Subject subjectForAssignment;

    @OneToMany(mappedBy = "assignmentEssayQ", cascade = CascadeType.ALL ,orphanRemoval = true , fetch = FetchType.LAZY)
    private List<EssayQuestion> EssayList = new ArrayList<>();
    @OneToMany(mappedBy = "assignmentQuestions", cascade = CascadeType.ALL ,orphanRemoval = true , fetch = FetchType.LAZY)
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getNoOfAttempts() {
        return noOfAttempts;
    }

    public void setNoOfAttempts(int noOfAttempts) {
        this.noOfAttempts = noOfAttempts;
    }

    public List<Marks> getMarksSet() {
        return marksSet;
    }

    public void setMarksSet(List<Marks> marksSet) {
        this.marksSet = marksSet;
    }

    public List<EssayQuestion> getEssayList() {
        return EssayList;
    }

    public void setEssayList(List<EssayQuestion> essayList) {
        EssayList = essayList;
    }


}


