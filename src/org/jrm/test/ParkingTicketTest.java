package org.jrm.test;

import org.jrm.data.ticket.ParkingTicket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTicketTest {

    ParkingTicket pt;
    Float minCharge = 5f;
    Float maxTicketCharge = 15f;
    Float lostTicketCharge = 25f;

    @BeforeEach
    void setUp()
    {
        pt = new ParkingTicket("XXXX-XXXX-XXXX-XXXX", "2018-09-29 02:22");
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
        assertEquals(minCharge, pt.getCharge("2018-09-29 04:20"), "Minimum charge should be $5.00 for exactly two hours");
        assertEquals(minCharge, pt.getCharge("2018-09-29 04:23"), "Minimum charge should be $5.00 for two hours and one minute");
        assertNotEquals(minCharge, pt.getCharge("2018-09-29 05:23"), "Minimum charge should be more than $5.00 for three hours and one minute");
    }

    @Test
    @DisplayName("Maximum charge test")
    void maxCharge()
    {
        assertEquals(maxTicketCharge, pt.getCharge("2018-09-29 15:22"), "Maximum charge for 13 hours with ticket should be $15.00");
        assertEquals(maxTicketCharge, pt.getCharge("2018-09-29 14:23"), "Maximum charge for 12 hours and 1 minute with ticket should be $15.00");
        assertEquals(maxTicketCharge, pt.getCharge("2018-09-30 02:22"), "Maximum charge for 24 hours with ticket should be $15.00");
    }

}