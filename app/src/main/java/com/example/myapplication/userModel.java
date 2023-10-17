package com.example.myapplication;


public  class userModel {
    private  String userId,username,user_password,user_email;
    public userModel(){

    }

    public userModel(String userId, String username, String user_password, String user_email) {
        this.userId = userId;
        this.username = username;
        this.user_password = user_password;
        this.user_email = user_email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}