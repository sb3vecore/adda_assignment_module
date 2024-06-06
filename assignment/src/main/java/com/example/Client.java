package com.example;
import java.time.LocalTime;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {
        // Create an apartment complex
        Apartment apartment = new Apartment(1);

        // Create facilities
        Facility clubhouse = apartment.createFacility("Clubhouse");
        Facility tennisCourt = apartment.createFacility("Tennis Court");

        // Add facilities to apartment
        apartment.createFacility("clubhouse");
        apartment.createFacility("tennis court");

        // Add slots to facilities
        clubhouse.addSlot("10:00", "16:00", 100);
        clubhouse.addSlot("16:00", "22:00", 500);
        tennisCourt.addSlot("00:00", "23:59", 50);

        // Create a booking manager
        BookingManager bookingManager = new BookingManager(apartment);

        // Create users
        User user1 = new User(1, 1, 32000, bookingManager);
        User user2 = new User(2, 1, 13000, bookingManager);

        // Define the input array
        String[] inputs = {
                "Clubhouse, 26-10-2020, 16:00 - 22:00",
                "Tennis COUrt, 26-10-2020, 16:00 - 20:00",
                "Clubhouse, 26-10-2020, 17:00 - 21:00"
        };

        // Process each booking request
        for (String input : inputs) {
            // Split the input string
            String[] parts = input.split(", ");
            String facilityName = parts[0];
            String date = parts[1];
            String[] times = parts[2].split(" - ");
            LocalTime startTime = LocalTime.parse(times[0]);
            LocalTime endTime = LocalTime.parse(times[1]);

            // Make booking requests
            user1.bookRequest(facilityName, date, startTime, endTime);
        }
    }
}
