package com.example;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.text.SimpleDateFormat;

public class Facility {

    public String name;
    public int apartmentID;
    public ArrayList<Slot> slots;
    public HashMap<Date, ArrayList<Booking>> bookingsByDate;

    Facility(int apartmentID, String name) {
        this.apartmentID = apartmentID;
        this.name = name;
        this.slots = new ArrayList<>();
        this.bookingsByDate = new HashMap<>();
    }

    public void addSlot(String startTimeString, String endTimeString, double price) {

        try {
            LocalTime startTime = LocalTime.parse(startTimeString);
            LocalTime endTime = LocalTime.parse(endTimeString); // convert the request strings to the respective formats
            if (startTime.isAfter(endTime) || price <= 0) {
                System.err.println("Invalid slot.");
                return;
            }
            // if the date exists, add it to the existing arraylist.
            // if not, create the key, create a corresponding slot list and add it.
            slots.add(new Slot(startTime, endTime, price));
        } catch (Exception e) {
            System.err.println("Invalid date entered.\n" + e.toString());
        }
    }

    public void bookSlot(Date date, Booking booking) {
        bookingsByDate.putIfAbsent(date, new ArrayList<Booking>());
        bookingsByDate.get(date).add(booking);
    }

    public boolean isBookingAvailable(Date date, LocalTime startTime, LocalTime endTime) {

        if (startTime.isAfter(endTime)) {// invalid input
            System.out.println("Booking failed, invalid input.");
            return false;
        }

        ArrayList<Booking> bookings = bookingsByDate.get(date);
        LocalTime earliest = LocalTime.MAX; //preventing the booking time to be before the earliest slot or after the latest slot.
        LocalTime latest = LocalTime.MIN;  
        for (Slot slot : slots) {
            if (slot.startTime.isBefore(earliest)) {
                earliest = slot.startTime;
            }
            if (slot.endTime.isAfter(latest)) {
                latest = slot.endTime;
            }
        }

        if (earliest.isAfter(startTime) || latest.isBefore(endTime)) {
            System.out.println("Booking failed, invalid input.");
            return false;
        }
        if (bookings == null) { // if no bookings exist on the given date
            return true;
        } else { 
            // check each slot for the lowest bound startTime.
            // iterate till the closest end time is found.
            for (Booking booking : bookings) {
                // System.out.println(endTime  + " " + booking.startTime);
                if (startTime.equals(booking.startTime) || endTime.equals(booking.endTime))
                    return false;
                else if (startTime.isBefore(booking.endTime) && endTime.isAfter(booking.startTime))
                    return false;
                else if (startTime.isAfter(booking.startTime) && endTime.isBefore(booking.endTime))
                    return false; 
            }
            return true;
        }
    }
}