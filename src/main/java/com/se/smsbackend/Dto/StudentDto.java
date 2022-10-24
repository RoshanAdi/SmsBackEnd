package com.se.smsbackend.Dto;

import com.se.smsbackend.Entity.Student;

public class StudentDto {
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String fullName;
    private String birthDate;
    private String age;
    private String username;
    private String tp;
    private String address;
    private String password;
    private String Role;
    private int StudentId;
    private String verificationCode;

    public Student getStudentFromDto(){
        Student student = new Student();
        student.setStudentId(StudentId);
        student.setRole(Role);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setFullName(fullName);
        student.setEnabled(enabled);
        student.setPassword(password);
        student.setAddress(address);
        student.setAge(age);
        student.setUsername(username);
        student.setTp(tp);
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
