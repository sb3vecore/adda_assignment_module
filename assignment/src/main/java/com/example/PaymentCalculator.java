package com.example;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class PaymentCalculator {

    public double calculatePayment(Booking booking, Date date, Facility facility) {

        double total = 0;
        LocalTime startTime = booking.startTime;
        LocalTime endTime = booking.endTime;
        ArrayList<Slot> slots = facility.slots;
        if (slots != null) {
            LocalTime earliest = LocalTime.MAX;
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
                return -1;
            }
            
            for (Slot slot : slots) {
                if (!(endTime.isBefore(slot.startTime) || startTime.isAfter(slot.endTime))) {
                    LocalTime effectiveStart = startTime.isAfter(slot.startTime) ? startTime : slot.startTime;
                    LocalTime effectiveEnd = endTime.isBefore(slot.endTime) ? endTime : slot.endTime;
                    double hours = (double) (effectiveEnd.toSecondOfDay() - effectiveStart.toSecondOfDay()) / 3600;
                    total += hours * slot.rate;
                }
            }
        }
        return total;
    }

}
