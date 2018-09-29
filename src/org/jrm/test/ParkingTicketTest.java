package org.jrm.test;

import org.jrm.data.ticket.ParkingTicket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTicketTest {

    ParkingTicket pt;
    Float minCharge = 5f;
    Float maxTicketCharge = 15f;
    Float lostTicketCharge = 25f;

    @BeforeEach
    void setUp()
    {
        pt = new ParkingTicket("XXXX-XXXX-XXXX-XXXX", "09-29-2018 12:22", 3f);
    }

    @AfterEach
    void tearDown()
    {
        pt = null;
    }

    @Test
    @DisplayName("Minimum charge test")
    void minCharge()
    {
        assertEquals(minCharge, pt.getCharge(), "Minimum charge should be $5.00");
    }

    @Test
    @DisplayName("Maximum charge test")
    void maxCharge()
    {
        assertEquals(maxTicketCharge, pt.getCharge(), "Maximum charge with ticket should be $15.00");
    }

    @Test
    @DisplayName("No ticket charge test")
    void ncCharge()
    {
        assertEquals(lostTicketCharge, pt.getCharge(), "Lost ticket should charge should be $25.00");
    }
}