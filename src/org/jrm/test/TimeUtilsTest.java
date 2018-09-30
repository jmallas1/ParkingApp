package org.jrm.test;

import org.jrm.util.TimeUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    String sDate1;
    String sDate2;
    Date date1;
    Date date2;
    Date date3;
    Integer hourSpan;

    @BeforeEach
    void setUp()
    {
        sDate1 = "1975-12-28 05:15";
        sDate2 = "2012-12-21 03:14";
        date1 = new GregorianCalendar(1975, 11, 28, 5, 15).getTime();
        date2 = new GregorianCalendar(2012, 11, 21, 3, 14).getTime();
        date3 = new GregorianCalendar(2012, 11, 21, 8, 14).getTime();
        hourSpan = 5;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void stringDateToDate()
    {
        assertEquals(date1, TimeUtils.stringDateToDate(sDate1), "Strings should equal dates");
        assertEquals(date2, TimeUtils.stringDateToDate(sDate2), "Strings should equal dates");
    }

    @Test
    void dateToString()
    {
        assertEquals(sDate1, TimeUtils.dateToString(date1), "Dates should equal strings");
        assertEquals(sDate2, TimeUtils.dateToString(date2), "Dates should equal strings");
    }

    @Test
    void randomTimeString()
    {
        Date myDate = TimeUtils.stringDateToDate(TimeUtils.randomTimeString(date1, date2));
        assertTrue(myDate.getTime() > date1.getTime(), "Random date before Date1");
        assertTrue(myDate.getTime() < date2.getTime(), "Random date after Date2");
    }

    @Test
    void getHours()
    {
        assertEquals(hourSpan, TimeUtils.getHours(date2, date3), "Date 2 and 3 happened 5 hours apart");
    }
}