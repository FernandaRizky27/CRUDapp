package com.example.crudapp;

public class HelperClass {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    String email;
    String username;
    String password;
    String confirm_password;

    public HelperClass(String confirm_password, String password, String username, String email) {
        this.confirm_password = confirm_password;
        this.password = password;
        this.username = username;
        this.email = email;
    }


}
