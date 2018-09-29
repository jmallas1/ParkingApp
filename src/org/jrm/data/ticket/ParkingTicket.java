package org.jrm.data.ticket;

import org.jrm.util.TimeUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    public Float getCharge(Date timeOut)
    {
        Float totalCharge;

        Integer hourDif = getHours(timeOut);

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

    private Integer getHours(Date timeOut)
    {
        Long timeDif = timeOut.getTime() - this.timeIn.getTime();

        Long dayDif = TimeUnit.MILLISECONDS.toDays(timeDif);
        Long remainingHours = timeDif - TimeUnit.DAYS.toMillis(dayDif);
        Long hourDif = TimeUnit.MILLISECONDS.toHours(remainingHours);
        Long remainingMinutes = remainingHours - TimeUnit.HOURS.toMillis(hourDif);
        Long minuteDif = TimeUnit.MILLISECONDS.toMinutes(remainingMinutes);

        if(dayDif > 0)
        {
            hourDif += (24 * dayDif);
        }
        if(minuteDif > 0)
        {
            hourDif++;
        }

        return hourDif.intValue();
    }
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