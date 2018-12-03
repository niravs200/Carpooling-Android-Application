package com.example.bhavneet.car;

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
    @SerializedName("start_latitude")
    @Expose
    private double start_latitude;
    @SerializedName("start_longitude")
    @Expose
    private double start_longitude;
    @SerializedName("destination_latitude")
    @Expose
    private double destination_latitude;
    @SerializedName("destination_longitude")
    @Expose
    private double destination_longitude;
    @SerializedName("destination")
    @Expose
    private String destination;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @SerializedName("Source")
    @Expose
    private String source;
    public double getStart_latitude() {
        return start_latitude;
    }

    public void setStart_latitude(double start_latitude) {
        this.start_latitude = start_latitude;
    }

    public double getStart_longitude() {
        return start_longitude;
    }

    public void setStart_longitude(double start_longitude) {
        this.start_longitude = start_longitude;
    }

    public double getDestination_latitude() {
        return destination_latitude;
    }

    public void setDestination_latitude(double destination_latitude) {
        this.destination_latitude = destination_latitude;
    }

    public double getDestination_longitude() {
        return destination_longitude;
    }

    public void setDestination_longitude(double destination_longitude) {
        this.destination_longitude = destination_longitude;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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
    private int status;


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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}