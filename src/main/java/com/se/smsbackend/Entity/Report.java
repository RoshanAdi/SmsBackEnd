package com.se.smsbackend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subjectId;
    private String subjectName;
    private String assignmentId;
    private int totalParticipants;
    private int totalMarks;
    private String averageMarks;
    private int below30;
    private int noOfStudBetween30and55;
    private int noOfStudBetween55and65;
    private int noOfStudBetween65and75;
    private  int noOfStudAbove75;
    private int outOf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public void setTotalParticipants(int totalParticipants) {
        this.totalParticipants = totalParticipants;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getAverageMarks() {
        return averageMarks;
    }

    public void setAverageMarks(String averageMarks) {
        this.averageMarks = averageMarks;
    }

    public int getBelow30() {
        return below30;
    }

    public void setBelow30(int below30) {
        this.below30 = below30;
    }

    public int getNoOfStudBetween30and55() {
        return noOfStudBetween30and55;
    }

    public void setNoOfStudBetween30and55(int noOfStudBetween30and55) {
        this.noOfStudBetween30and55 = noOfStudBetween30and55;
    }

    public int getNoOfStudBetween55and65() {
        return noOfStudBetween55and65;
    }

    public void setNoOfStudBetween55and65(int noOfStudBetween55and65) {
        this.noOfStudBetween55and65 = noOfStudBetween55and65;
    }

    public int getNoOfStudBetween65and75() {
        return noOfStudBetween65and75;
    }

    public void setNoOfStudBetween65and75(int noOfStudBetween65and75) {
        this.noOfStudBetween65and75 = noOfStudBetween65and75;
    }

    public int getNoOfStudAbove75() {
        return noOfStudAbove75;
    }

    public void setNoOfStudAbove75(int noOfStudAbove75) {
        this.noOfStudAbove75 = noOfStudAbove75;
    }

    public int getOutOf() {
        return outOf;
    }

    public void setOutOf(int outOf) {
        this.outOf = outOf;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
