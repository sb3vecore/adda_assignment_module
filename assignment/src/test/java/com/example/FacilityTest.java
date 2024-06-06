package com.example;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class FacilityTest {
    private Apartment apt;

    @Before 
    public void startup() {
        apt = new Apartment(2);
        Facility f1 = apt.createFacility("gym");
        Facility f2 = apt.createFacility("swimming pool");
    }

    @Test
    public void testAddSlot() { 
        Facility f1 = apt.findFacility("gym");
        f1.addSlot("10:00", "16:00", 100);
        assertNotNull(f1.slots); //returns true, valid and successful slot.
        f1 = apt.findFacility("swimming pool");
        f1.addSlot("20:00", "22:00", -500); //invalid, negative money.
        assertEquals(f1.slots.size(), 0);
        f1.addSlot("23:00", "22:00", 500); //invalid, start date is greater than end date.
        assertEquals(f1.slots.size(), 0); 
    }

    @Test
    public void testIsBookingAvailable() {

        Facility f1 = apt.findFacility("gym");
        f1.addSlot("10:00", "16:00", 100);

        Date date = new Date();

        // Specify start and end times for the booking
        LocalTime startTime = LocalTime.of(7, 0);
        LocalTime endTime = LocalTime.of(12, 0); //required output: "invalid booking"

        // Check if booking is available
        boolean available = f1.isBookingAvailable(date, startTime, endTime);
        System.out.println(available);
        assertEquals(available, false);

    }
}
