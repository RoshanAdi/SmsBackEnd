package com.se.smsbackend.Entity;

import javax.persistence.*;

@Entity
public class EssayAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT", length = 4000)
    @Lob
    private String essayAnswersList;
private String assigmentID;
    private String username;
    private int marks;
    private String comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getAssigmentID() {
        return assigmentID;
    }

    public void setAssigmentID(String assigmentID) {
        this.assigmentID = assigmentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEssayAnswersList() {
        return essayAnswersList;
    }

    public void setEssayAnswersList(String essayAnswersList) {
        this.essayAnswersList = essayAnswersList;
    }
}
