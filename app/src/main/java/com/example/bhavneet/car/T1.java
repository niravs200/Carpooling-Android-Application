package com.example.bhavneet.car;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class T1 {

    @SerializedName("TP")
    @Expose
    private String tP;

    public String getTP() {
        return tP;
    }

    public void setTP(String tP) {
        this.tP = tP;
    }

}