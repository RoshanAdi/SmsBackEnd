package com.se.smsbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class McqQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;
    private String choice6;
    private boolean ans1;
    private boolean ans2;
    private boolean ans3;
    private boolean ans4;
    private boolean ans5;
    private boolean ans6;

    @ManyToOne
    @JoinColumn(name = "QuestionOfAssi", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Assignment assignmentQuestions;

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

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getChoice5() {
        return choice5;
    }

    public void setChoice5(String choice5) {
        this.choice5 = choice5;
    }

    public String getChoice6() {
        return choice6;
    }

    public void setChoice6(String choice6) {
        this.choice6 = choice6;
    }

    public Assignment getAssignmentQuestions() {
        return assignmentQuestions;
    }

    public void setAssignmentQuestions(Assignment assignmentQuestions) {
        this.assignmentQuestions = assignmentQuestions;
    }

    public boolean isAns1() {
        return ans1;
    }

    public void setAns1(boolean ans1) {
        this.ans1 = ans1;
    }

    public boolean isAns2() {
        return ans2;
    }

    public void setAns2(boolean ans2) {
        this.ans2 = ans2;
    }

    public boolean isAns3() {
        return ans3;
    }

    public void setAns3(boolean ans3) {
        this.ans3 = ans3;
    }

    public boolean isAns4() {
        return ans4;
    }

    public void setAns4(boolean ans4) {
        this.ans4 = ans4;
    }

    public boolean isAns5() {
        return ans5;
    }

    public void setAns5(boolean ans5) {
        this.ans5 = ans5;
    }

    public boolean isAns6() {
        return ans6;
    }

    public void setAns6(boolean ans6) {
        this.ans6 = ans6;
    }
}
