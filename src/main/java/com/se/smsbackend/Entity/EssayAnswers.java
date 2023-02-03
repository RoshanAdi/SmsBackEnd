package com.se.smsbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class EssayAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

/*    @Column(columnDefinition = "TEXT", length = 4000)
    @Lob*/


private String assigmentID;
    private String username;
    private String answer;
    private int marks;
    private String comments;
    private String updateId;
    @ManyToOne
    @JoinColumn(name = "question", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public EssayQuestion question;

    @ManyToOne
    @JoinColumn(name = "student", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Student student;
    public EssayQuestion getQuestion() {
        return question;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(EssayQuestion question) {
        this.question = question;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
}
