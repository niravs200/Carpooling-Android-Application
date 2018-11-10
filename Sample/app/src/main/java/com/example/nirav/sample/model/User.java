package com.example.nirav.sample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("source")
    @Expose
    private Object source;
    @SerializedName("destination")
    @Expose
    private Object destination;
    @SerializedName("time")
    @Expose
    private Object time;
    @SerializedName("date")
    @Expose
    private Object date;
    @SerializedName("capacity")
    @Expose
    private Object capacity;
    @SerializedName("role")
    @Expose
    private Object role;
    @SerializedName("status")
    @Expose
    private Object status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getDestination() {
        return destination;
    }

    public void setDestination(Object destination) {
        this.destination = destination;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Object getCapacity() {
        return capacity;
    }

    public void setCapacity(Object capacity) {
        this.capacity = capacity;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

}