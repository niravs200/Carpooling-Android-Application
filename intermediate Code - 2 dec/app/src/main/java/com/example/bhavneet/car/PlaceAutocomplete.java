package com.example.bhavneet.car;

public class PlaceAutocomplete {
    public CharSequence placeID;
    public CharSequence description;

    PlaceAutocomplete(CharSequence placeID, CharSequence descritpion){
        this.placeID = placeID;
        this.description = descritpion;

    }

    @Override
    public String toString(){
        return description.toString();
    }
}
