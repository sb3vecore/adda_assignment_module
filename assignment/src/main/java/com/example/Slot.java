package com.example;
import java.time.LocalTime;

public class Slot {

    public LocalTime startTime;
    public LocalTime endTime;
    public double rate;

    public Slot(LocalTime startTime, LocalTime endTime, double rate) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.rate = rate;
    }
}
