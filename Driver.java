package com.example.bhavneet.trial;

public class Driver {
    String driver_id;
    String driver_name;
    int phone_number;

    String constant = "D";
    int count=1;
    public Driver(String driver_id, String driver_name, int phone_number){
        this.driver_id = constant+count;
        this.driver_name = driver_name;
        this.phone_number = phone_number;
        count+=1;
    }

    public void setDriver_name(String driver_name){
        this.driver_name = driver_name;
    }

    public void setPhone_number (int phone_number){
        this.phone_number = phone_number;
    }

    public String getDriver_name(){
        return this.driver_name;
    }

    public int getPhone_number(){
        return this.phone_number;
    }

    public String getDriver_id(){
        return this.driver_id;
    }
}
