package com.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class PaymentCalculatorTest {

    private Apartment apartment;
    private PaymentCalculator paymentCalculator;
    private Facility facility;
    private ArrayList<Slot> slots;

    @Before
    public void setUp() {
        apartment = new Apartment(1);
        paymentCalculator = new PaymentCalculator();
        slots = new ArrayList<>();
        slots.add(new Slot(LocalTime.of(9, 0), LocalTime.of(11, 0), 100.0));
        slots.add(new Slot(LocalTime.of(11, 0), LocalTime.of(13, 0), 150.0));
        facility = new Facility(apartment.apartmentID, "Gym");
    }

//successful cases handled in client code.

    @Test
    public void testCalculatePayment_TimesOutsideSlots() throws Exception {
        Booking booking = new Booking(LocalTime.of(10, 0), LocalTime.of(12, 0));
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2024");
        double payment = paymentCalculator.calculatePayment(booking, date, facility);
        assertEquals(-1.0, payment, 0.00);
    }
}