package com.se.smsbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;
    private String subjectName;
    @ColumnDefault("0")
    private boolean active ;
    @Column(length = 1000)
    private String description;
    private String createdBy;
    private String createrFirstName;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Subject_Teacher",joinColumns = @JoinColumn(name = "subjectId", referencedColumnName = "subjectId"),inverseJoinColumns = @JoinColumn(name = "TeacherId", referencedColumnName = "TeacherId")    )
    @JsonIgnore
    private Set<Teacher> teachers;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Subject_Student",joinColumns = @JoinColumn(name = "subjectId", referencedColumnName = "subjectId"),inverseJoinColumns = @JoinColumn(name = "StudentId", referencedColumnName = "StudentId")    )
    @JsonIgnore
    private List<Student> students = new ArrayList<>();


    @OneToMany(mappedBy = "subjectForAssignment", cascade = CascadeType.ALL,orphanRemoval = true , fetch = FetchType.LAZY)
    private List<Assignment> AssigmentList = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL,orphanRemoval = true , fetch = FetchType.LAZY)
    private List<SubjectMarks> subjectMarks = new ArrayList<>();
    public String getCreaterFirstName() {
        return createrFirstName;
    }

    public void setCreaterFirstName(String createrFirstName) {
        this.createrFirstName = createrFirstName;
    }



    public List<Assignment> getAssigmentList() {
        return AssigmentList;
    }

    public void setAssigmentList(List<Assignment> assigmentList) {
        AssigmentList = assigmentList;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<SubjectMarks> getSubjectMarks() {
        return subjectMarks;
    }

    public void setSubjectMarks(List<SubjectMarks> subjectMarks) {
        this.subjectMarks = subjectMarks;
    }
}
