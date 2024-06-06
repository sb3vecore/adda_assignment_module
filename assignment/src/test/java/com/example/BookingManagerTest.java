package com.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class BookingManagerTest {

    private Apartment apartment;
    private BookingManager bookingManager;
    private User user;
    private Facility facility;

    @Before
    public void setUp() {
        apartment = new Apartment(2);
        bookingManager = new BookingManager(apartment);
        ArrayList<Slot> slots = new ArrayList<Slot>();
        slots.add(new Slot(LocalTime.of(9, 0), LocalTime.of(11, 0), 100.0));
        slots.add(new Slot(LocalTime.of(11, 0), LocalTime.of(13, 0), 100.0));
        facility = new Facility(2, "Gym");
        apartment.addFacility(facility);
    }

    @Test
    public void testCheckAvailabilityAndPay_InsufficientBalance() {
        // Initialise a user with insufficient balance.
        // They can't afford the booking
        // Should be false
        user = new User(1, 2, 50.0, this.bookingManager);
        Booking booking = new Booking(LocalTime.of(10, 0), LocalTime.of(12, 0));
        String dateString = "01-01-2024";
        boolean result = bookingManager.checkAvailabilityAndPay(user, "Gym", dateString, booking);
        assertFalse(result);
    }
}
