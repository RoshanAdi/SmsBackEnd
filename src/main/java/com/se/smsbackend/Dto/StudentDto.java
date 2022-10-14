package com.se.smsbackend.Dto;

import com.se.smsbackend.Entity.Student;

public class StudentDto {
    private boolean enabled;
    private String FirstName;
    private String LastName;
    private String FullName;  
    private String birthDate;
    private String Age;
    private String username;
    private String Tp;
    private String Address;
    private String password;
    private String Role;
    private int StudentId;
    private String verificationCode;

    public Student getStudentFromDto(){
        Student student = new Student();
        student.setStudentId(StudentId);
        student.setRole(Role);
        student.setFirstName(FirstName);
        student.setLastName(LastName);
        student.setFullName(FullName);
        student.setEnabled(enabled);
        student.setPassword(password);
        student.setAddress(Address);
        student.setAge(Age);
        student.setUsername(username);
        student.setTp(Tp);
        student.setBirthDate(birthDate);
        student.setVerificationCode(verificationCode);

        return student;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTp() {
        return Tp;
    }

    public void setTp(String tp) {
        Tp = tp;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
