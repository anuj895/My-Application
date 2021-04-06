package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class UserResponse implements Serializable {
    private int code;
    private Meta meta;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    private ArrayList<Pojo> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Pojo> getData() {
        return data;
    }

    public void setData(ArrayList<Pojo> data) {
        this.data = data;
    }
}
