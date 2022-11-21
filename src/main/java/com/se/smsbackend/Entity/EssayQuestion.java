package com.se.smsbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class EssayQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String marks;

    @ManyToOne
    @JoinColumn(name = "AssiQuestions", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Assignment assignmentEssayQ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public Assignment getAssignmentEssayQ() {
        return assignmentEssayQ;
    }

    public void setAssignmentEssayQ(Assignment assignmentEssayQ) {
        this.assignmentEssayQ = assignmentEssayQ;
    }
}
