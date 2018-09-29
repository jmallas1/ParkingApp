package org.jrm;

import org.jrm.util.TimeUtils;

import java.util.Date;

public class Main {

    public static void main(String[] args)
    {
        Date dt1 = TimeUtils.stringDateToDate("2018-09-29 07:00");
        Date dt2 = TimeUtils.stringDateToDate("2018-09-29 11:00");

        String someString = TimeUtils.randomTimeString(dt1, dt2);
        someString = TimeUtils.randomTimeString(dt1, dt2);
        someString = TimeUtils.randomTimeString(dt1, dt2);
        someString = TimeUtils.randomTimeString(dt1, dt2);
        someString = TimeUtils.randomTimeString(dt1, dt2);

        System.out.println(someString);
    }
}
