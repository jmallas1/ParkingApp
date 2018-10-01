package org.jrm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TimeUtils
{
    public TimeUtils()
    {

    }

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

    public static String dateToString(Date sDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(sDate);
    }

    public static String randomTimeString(Date minDate, Date maxDate)
    {
        Date workingDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        workingDate.setTime(ThreadLocalRandom.current().nextLong(minDate.getTime(), maxDate.getTime()));
        return sdf.format(workingDate);
    }

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
