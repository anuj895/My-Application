package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pojo {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;

    @SerializedName("gender")
    private String gender;

    @SerializedName("status")
    private String status;

    public Pojo(String name, String email, String gender, String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

}
