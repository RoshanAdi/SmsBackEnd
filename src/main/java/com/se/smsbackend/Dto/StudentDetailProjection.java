package com.se.smsbackend.Dto;

public class StudentDetailProjection {

    private final String Username;
    private final String birthDate;
    private final String fullName;
    private final String lastName;
    private final String firstName;
    private final String age;
    private final String tp;
    private final String address;
    private final String role;




    public StudentDetailProjection(String username, String birthDate, String fullName, String lastName, String firstName, String age, String tp, String address, String role) {
        Username = username;
        this.birthDate = birthDate;
        this.fullName = fullName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.tp = tp;
        this.address = address;
        this.role = role;
    }

    public String getUsername() {
        return Username;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAge() {
        return age;
    }

    public String getTp() {
        return tp;
    }

    public String getAddress() {
        return address;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }
}
