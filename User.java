package com.example.bhavneet.trial;

public class User {

    String user_id;
    String user_name;
    int phone_number;

    String constant = "U";
    int count=1;
    public User(String user_id, String user_name, int phone_number){
        this.user_id = constant+count;
        this.user_name = user_name;
        this.phone_number = phone_number;
        count+=1;
    }

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    public void setPhone_number (int phone_number){
        this.phone_number = phone_number;
    }

    public String getUser_name(){
        return this.user_name;
    }

    public int getPhone_number(){
        return this.phone_number;
    }

    public String getUser_id(){
        return this.user_id;
    }

}
