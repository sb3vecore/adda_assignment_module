package com.example;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;


public class ApartmentTest {

    private Apartment apt;

    @Before
    public void startup() {
        apt = new Apartment(2);
        Facility f1 = apt.createFacility("gym");
        Facility f2 = apt.createFacility("swimming pool");
    }

    @Test
    public void testCreateFacility () { //testing facility creation
        Facility fac = apt.createFacility("pARty HALL");
        assertNotNull(fac);
        assertEquals("party hall", fac.name); //testing if it has been made lowercase.
        assertEquals(2, fac.apartmentID);
        assertTrue(apt.facilities.contains(fac)); //check if the arraylist contains the facility.
    }

    @Test
    public void testFindFacility() {
        Facility fac = apt.findFacility("swimming pool");
        assertNotNull("Facility 'swimming pool' should be found", fac);
        assertEquals("swimming pool", fac.name); // Ensure correct case
    }
    
}
