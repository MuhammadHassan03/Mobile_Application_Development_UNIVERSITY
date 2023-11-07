package com.example.assignment04;

public class User {

    String Name;
    String Email;
    String Pass;
    String Address;

    public User(String name, String email, String pass, String address) {
        Name = name;
        Email = email;
        Pass = pass;
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
