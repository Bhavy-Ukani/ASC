package com.asc.model;

public class HomeModel {
    private String avtar,mail,name,type,username;

    public HomeModel() {
    }

    public HomeModel(String avtar, String mail, String name, String type, String username) {
        this.avtar = avtar;
        this.mail = mail;
        this.name = name;
        this.type = type;
        this.username = username;
    }

    public String getAvtar() {
        return avtar;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }
}
