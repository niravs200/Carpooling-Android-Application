package com.example.bhavneet.trial;

public class Rides {

    String user_id;
    String driver_id;
    String start_location;
    String end_location;
    String journey_time;
    String status;

    public Rides(String user_id, String driver_id, String start_location, String end_location, String journey_time, String status){
        this.user_id = user_id;
        this.driver_id = driver_id;
        this.start_location = start_location;
        this.end_location = end_location;
        this.journey_time = journey_time;
        this.status = status;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public void setStart_location(String start_location) {
        this.start_location = start_location;
    }

    public void setEnd_location(String end_location) {
        this.end_location = end_location;
    }

    public void setJourney_time(String journey_time) {
        this.journey_time = journey_time;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getUser_id() {
        return user_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public String getStart_location() {
        return start_location;
    }

    public String getEnd_location() {
        return end_location;
    }

    public String getJourney_time() {
        return journey_time;
    }

    public String getStatus() {
        return status;
    }



}
