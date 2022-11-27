package com.se.smsbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int StudentId;


    @OneToMany(mappedBy = "studentForAttendance")
    private List<Attendance> AttendanceList = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL,orphanRemoval = true , fetch = FetchType.LAZY)
    private Set<Marks> marks = new HashSet<>();

    private String firstName;
    private String lastName;
    private String fullName;
    private String birthDate;
    private String age;
    private String username;      ///username stands for email
    private String tp;
    private String address;
    private String password;

    private String role;

    @Column(columnDefinition = "TEXT", length = 255)
    @Lob
    private String verificationCode;

    private boolean enabled;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EssayAnswers> essayAnswers;
    @ManyToMany (mappedBy = "students", cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Subject> subjects = new HashSet<>();

    public List<Attendance> getAttendanceList() {
        return AttendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        AttendanceList = attendanceList;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }





    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Marks> getMarks() {
        return marks;
    }

    public void setMarks(Set<Marks> marks) {
        this.marks = marks;
    }

    public List<EssayAnswers> getEssayAnswers() {
        return essayAnswers;
    }

    public void setEssayAnswers(List<EssayAnswers> essayAnswers) {
        this.essayAnswers = essayAnswers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentId=" + StudentId +
                ", AttendanceList=" + AttendanceList +
                ", marks=" + marks +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", age='" + age + '\'' +
                ", username='" + username + '\'' +
                ", tp='" + tp + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", enabled=" + enabled +
                ", essayAnswers=" + essayAnswers +
                ", subjects=" + subjects +
                '}';
    }
}

