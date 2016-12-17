package com.example.sco.imuvo.Model;

public class User {
    private long id;
    private String UserName;
    private String password;

    public User(long id, String userName, String password) {
        this.id = id;
        UserName = userName;
        this.password = password;
    }
    public User(String userName, String password){
        UserName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
