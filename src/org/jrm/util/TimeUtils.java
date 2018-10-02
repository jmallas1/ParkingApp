package org.jrm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Class model providing static methods useful for working with time.
 * @author Jared Mallas
 * @version 1.0
 */
public class TimeUtils
{
    /**
     * Convert a string representation of a date (format: yyyy-MM-dd HH:mm) to a Date object
     * @param sDate string representation of a date (format: yyyy-MM-dd HH:mm)
     * @return a new Date object
     */
    public static Date stringDateToDate(String sDate)
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

    /**
     * Convert a given date object to a string representation of that date (format: yyyy-MM-dd HH:mm)
     * @param sDate a Date object
     * @return string representation of the given date (format: yyyy-MM-dd HH:mm)
     */
    public static String dateToString(Date sDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(sDate);
    }

    /**
     * Generate a random point in time between two Date objects
     * @param minDate any Date object
     * @param maxDate any Date object
     * @return String representation of a random point in time between the two Date objects
     */
    public static String randomTimeString(Date minDate, Date maxDate)
    {
        Date workingDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        workingDate.setTime(ThreadLocalRandom.current().nextLong(minDate.getTime(), maxDate.getTime()));
        return sdf.format(workingDate);
    }

    /**
     * Calculate the number of hours (rounding up) between two Date objects
     * @param startDate a Date object
     * @param endDate a Date object
     * @return Number of hours between date objects
     */
    public static Integer getHours(Date startDate, Date endDate)
    {
        Long timeDif = endDate.getTime() - startDate.getTime();

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
}
