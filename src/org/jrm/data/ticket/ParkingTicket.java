package org.jrm.data.ticket;

import org.jrm.util.TimeUtils;
import java.util.Date;
import java.util.UUID;

/**
 * Class model for a Parking ticket at a parking garage
 * Tickets created with default constructor (and overloaded
 * ParkingTicket(String sTimeIn) constructor) will remain unique
 * by way of java.util.UUID
 * @author Jared Mallas
 * @version 1.0
 */
public class ParkingTicket
{
    private String ticketID;
    private Date timeIn;

    public ParkingTicket()
    {
        this.ticketID = UUID.randomUUID().toString();
        this.timeIn = getTimeStamp();
    }

    public ParkingTicket(String sTimeIn)
    {
        this.ticketID = UUID.randomUUID().toString();
        this.timeIn = TimeUtils.stringDateToDate(sTimeIn);
    }

    public ParkingTicket(String ticketID, String sTimeIn)
    {
        /* use this as you're reading in the current "garage residents" */

        this.timeIn = TimeUtils.stringDateToDate(sTimeIn);

        this.ticketID = ticketID;
    }

    public Date getTimeStamp()
    {
        return new Date();
    }

    /**
     * Generate an appropriate charge for parking based on the difference between
     * garage entry (defined at ticket creation) and garage exit
     * @param timeOut Date / time (yyyy-MM-dd HH:mm) by which to calculate charge
     * @return Float representing the total charge for parking
     */
    public Float getCharge(Date timeOut)
    {
        Float totalCharge;

        Integer hourDif = TimeUtils.getHours(this.timeIn, timeOut);

        if(hourDif <= 3)
        {
            hourDif = 0;
        }
        else
        {
            hourDif -=3;
        }

        totalCharge = 5f + hourDif.floatValue();

        if (totalCharge > 15f)
        {
            return 15f;
        }
        else
        {
            return totalCharge;
        }
    }

    /**
     * Overloaded method of getCharge(Date timeOut) allowing the passing of a correctly
     * formatted string (yyyy-MM-dd HH:mm)
     * @param sTimeOut Formatted (yyyy-MM-dd HH:mm) string representation of a date
     * @return Float representing the total charge for parking
     */
    public Float getCharge(String sTimeOut)
    {
        return this.getCharge(TimeUtils.stringDateToDate(sTimeOut));
    }

    @Override
    public String toString()
    {
        return ticketID + ", " + TimeUtils.dateToString(timeIn);
    }

    /* getters and setters */

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }
}