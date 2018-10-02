package org.jrm.test;

import org.jrm.data.garage.Garage;
import org.jrm.pos.POSExit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

// TODO: WRITE TESTS

class POSExitTest {

    Garage ga;
    POSExit pe;

    @BeforeEach
    void setUp() {
        ga = new Garage("Some Garage");
        pe = new POSExit(ga);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateBill() {

        String bill = "Receipt for vehicle: MOCK CAR 1\n" +
                "4 hours parked: \n" +
                "2018-10-01 07:55 - 2018-10-01 11:55\n" +
                "$6.00\n" +
                "\n" +
                "\n";
        HashMap<String, String> billingDetails = new HashMap<>();
        billingDetails.put("id", "MOCK CAR 1");
        billingDetails.put("in", "2018-10-01 07:55");
        billingDetails.put("out", "2018-10-01 11:55");
        billingDetails.put("charge", "6.00");

        assertEquals(bill, pe.generateBill(billingDetails), "Bill generated");
    }
}