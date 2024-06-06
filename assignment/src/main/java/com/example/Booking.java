package com.example;
import java.time.LocalTime;

public class Booking {

    public LocalTime startTime;
    public LocalTime endTime;

    public Booking(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
