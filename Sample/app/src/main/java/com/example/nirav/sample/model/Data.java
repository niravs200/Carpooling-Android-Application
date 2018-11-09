package com.example.nirav.sample.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("t1")
    @Expose
    private List<T1> t1 = null;

    public List<T1> getT1() {
        return t1;
    }

    public void setT1(List<T1> t1) {
        this.t1 = t1;
    }

}