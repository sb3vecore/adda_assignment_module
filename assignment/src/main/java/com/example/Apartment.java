package com.example;
import java.util.ArrayList;

public class Apartment {

    public int apartmentID;
    public ArrayList<Facility> facilities;

    public Apartment(int apartmentID) {
        this.apartmentID = apartmentID;
        facilities = new ArrayList<Facility>();
    }

    public Facility createFacility(String name) {
        Facility facility = new Facility(this.apartmentID, name.toLowerCase());
        this.facilities.add(facility);
        return facility;
    }

    public void addFacility(Facility facility) {
        if (facility == null)
            return;
        facilities.add(facility);
    }

    public Facility findFacility (String facilityName) {
        String facilityNameLowercase = facilityName.toLowerCase(); //for consistent checking
        for (Facility facility : this.facilities) {
            if (facility.name.compareTo(facilityNameLowercase) == 0) {
                return facility;
            }
        }
        return null;
    }
}
