package org.jrm.data.ticket;

import java.util.Date;

public class ParkingTicket
{
    private Integer ticketNum;
    private Date timeIn;
    private Float parkingRate;

    public ParkingTicket()
    {
        /*
        something to generate the "next" ticket
        something to look up the current rate
        */
    }

    public ParkingTicket(Integer ticketNum, Date timeIn, Float parkingRate)
    {
        /* use this as you're reading in the current "garage residents" */
        this.ticketNum = ticketNum;
        this.timeIn = timeIn;
        this.parkingRate = parkingRate;
    }

}
