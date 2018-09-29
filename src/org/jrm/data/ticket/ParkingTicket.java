package org.jrm.data.ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        this.timeIn = new Date();
    }

    public ParkingTicket(String ticketID, String sTimeIn)
    {
        /* use this as you're reading in the current "garage residents" */

        this.timeIn = this.stringDateToDate(sTimeIn);

        this.ticketID = ticketID;
    }

    public Float getCharge(Date timeOut)
    {
        Float totalCharge;

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

        if(hourDif <= 3)
        {
            hourDif = 0l;
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

    public Float getCharge(String sTimeOut)
    {
        return this.getCharge(this.stringDateToDate(sTimeOut));
    }

    public static Float noTicket()
    {
        return 25f;
    }

    private Date stringDateToDate(String sDate)
    {
        Date someDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try
        {
            someDate = sdf.parse(sDate);
        }
        catch (ParseException e)
        {
            System.out.println("Unable to parse date in current format.");
            System.out.println("Expected: yyyy-MM-dd HH:mm");
            System.out.println("Actual: " + sDate);
            e.printStackTrace();
        }

        return someDate;
    }

    private String dateToString(Date sDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(sDate);
    }

    @Override
    public String toString()
    {
        return ticketID + ", " + dateToString(timeIn);
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