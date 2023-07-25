package com.cassidy.agromarket.models;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String age;
    private String region;
    private String phoneNumber;
    private String password;
    private byte[] image;
    private String email;


    public User(String firstName, String lastName, String gender, String age, String region, String phoneNumber, String password, byte[] image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gender = gender;
        this.image = image;
        this.email = email;
    }

    public User(String phoneNumber, String password) {

        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    // Getter and setter methods for the User class

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
