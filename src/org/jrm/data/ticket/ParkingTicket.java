package org.jrm.data.ticket;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class ParkingTicket
{
    private String ticketID;
    private Date timeIn;
    private Float parkingRate;

    public ParkingTicket()
    {
        this.ticketID = UUID.randomUUID().toString();
        this.timeIn = new Date();
        this.parkingRate = 3f;
    }

    public ParkingTicket(String ticketID, String timeIn, Float parkingRate)
    {
        /* use this as you're reading in the current "garage residents" */
        this.ticketID = ticketID;
        this.timeIn = new GregorianCalendar(2018, 9, 29, 8, 50).getTime();
        this.parkingRate = parkingRate;
    }

    public Float getCharge()
    {
        /* Use this to get the current 'charge' for this parking experience */
        return 3f;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "ticketID='" + ticketID + '\'' +
                ", timeIn=" + timeIn +
                ", parkingRate=" + parkingRate +
                '}';
    }
}
