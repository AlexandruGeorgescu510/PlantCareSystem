package com.example.pcs.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

public class LocationMarker {

    private double latitude;
    private double longitude;

    public LocationMarker() {
    }

    public LocationMarker(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    /**
     * These getters will be ignored when the object is serialized into a JSON object.
     * Since they will be ignored this means that they don't need a corresponding property (field)
     * in the Database. This is how we can return a value without having them declared as an instance variable.
     */

    @Exclude
    public LatLng getPosition(){
        return new LatLng(latitude, longitude);
    }

    @Exclude
    public String getTitle(){
        return Double.toString(latitude) + " " + Double.toString(longitude);
    }

}