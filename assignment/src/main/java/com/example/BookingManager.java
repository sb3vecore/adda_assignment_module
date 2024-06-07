package com.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class BookingManager {

    private Apartment apartment;

    public BookingManager(Apartment apartment) {
        this.apartment = apartment;
    }

    private double calculatePayment(Booking booking, Date date, Facility facility) {

        PaymentCalculator pCalculator = new PaymentCalculator();
        double result = pCalculator.calculatePayment(booking, date, facility);
        if (result <= 0)
            return -1.0;
        return result;
    }

    public boolean checkAvailabilityAndPay(User user, String facilityString, String dateString, Booking b) {

        Facility facility = apartment.findFacility(facilityString);
        if (facility == null) {
            System.out.println("Booking failed, facility does not exist.");
            return false;
        }

        // Parse date and time strings
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            // Check if the facility has available slots for the specified time
            if (!facility.isBookingAvailable(date, b.startTime, b.endTime)) {
                // System.out.println("Booking failed, already booked.");
                return false;
            }

            // Calculate payment
            double paymentAmount = calculatePayment(b, date, facility);
            if (paymentAmount <= 0) {
                System.out.println("Booking failed, invalid times.");
                return false;
            }
            // Check if the user has enough balance
            if (user.getBalance() < paymentAmount) {
                System.out.println("Booking failed, insufficient balance");
                return false;
            }

            // Perform the booking and transaction
            user.setBalance(user.getBalance() - paymentAmount);
            facility.bookSlot(date, b);
            System.out.println("Booked, Rs. " + paymentAmount);
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid date/time format");
            return false;
        }
    }
}