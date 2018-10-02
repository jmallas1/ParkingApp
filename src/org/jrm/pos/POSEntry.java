package org.jrm.pos;

import org.jrm.data.garage.Garage;
import org.jrm.data.ticket.ParkingTicket;
import org.jrm.util.POSUtils;
import org.jrm.util.TimeUtils;

import java.util.Date;

public class POSEntry
{
    private Boolean debug = true;
    private Garage location;
    private Boolean done = false;

    private ParkingTicket pt;
    private String userChoice;

    public POSEntry(Garage someGarage)
    {
        this.location = someGarage;

        displayBanner();

        while(!done)
        {
            userChoice = POSUtils.waitForInput();

            if (Integer.parseInt(userChoice) == 1)
            {
                if(debug)
                {
                    Date dt1 = TimeUtils.stringDateToDate("2018-09-30 07:00");
                    Date dt2 = TimeUtils.stringDateToDate("2018-09-30 12:00");
                    pt = new ParkingTicket(TimeUtils.randomTimeString(dt1, dt2));
                }
                else { pt = new ParkingTicket(); }

                location.pushTicket(pt);

                displayBanner();
            }
            else if (Integer.parseInt(userChoice) == 3)
            {
                location.closeGarage();
                done = true;
            }
        }
    }

    public void displayBanner()
    {
        System.out.println("Welcome to " + this.location.getName());
        System.out.println("=========================");
        System.out.println("\n");

        System.out.println("1 - Enter / print ticket");
        System.out.println("\n");
        System.out.println("3 - Close garage");
        System.out.println("\n");
        System.out.printf("=> ");
    }

}
