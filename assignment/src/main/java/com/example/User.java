package com.example;
import java.time.LocalTime;

public class User {

    public int userID;
    public int apartmentID;
    private BookingManager bManager;

    public User(int userID, int apartmentID, double balance, BookingManager bManager) {
        this.userID = userID;
        this.apartmentID = apartmentID;
        this.balance = balance;
        this.bManager = bManager;
    }

    private double balance;

    public void bookRequest(String facilityString, String date, LocalTime startTime, LocalTime endTime) {

        // instantiate the booking manager and let it handle everything
        Booking booking = new Booking(startTime, endTime);
        bManager.checkAvailabilityAndPay(this, facilityString, date, booking);
    }

    public int getApartmentID() {
        return this.apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
