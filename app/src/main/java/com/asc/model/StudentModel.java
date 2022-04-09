package com.asc.model;

public class StudentModel {
    private String name,email,batch,avatar;

    public StudentModel(String name, String email, String batch, String avatar) {
        this.name = name;
        this.email = email;
        this.batch = batch;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBatch() {
        return batch;
    }

    public String getAvatar() {
        return avatar;
    }
}
