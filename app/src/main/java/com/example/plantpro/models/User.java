package com.example.plantpro.models;

public class User {

    public String fullName, email, userCredential;

    public User(){

    }

    public User(String fullName, String email, String userCredential){
        this.fullName = fullName;
        this.email = email;
        this.userCredential = userCredential;
    }

}