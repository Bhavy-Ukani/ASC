package com.asc.model;

public class StudentModel {
    private String avtar,mail,name,type;

    public StudentModel() {
    }

    public StudentModel(String avtar, String mail, String name, String type) {
        this.avtar = avtar;
        this.mail = mail;
        this.name = name;
        this.type = type;
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
}
