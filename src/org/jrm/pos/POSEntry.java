package org.jrm.pos;

import org.jrm.data.garage.Garage;
import org.jrm.data.ticket.ParkingTicket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class POSEntry implements POSMachine
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
            userChoice = this.waitForInput();
            if (Integer.parseInt(userChoice) == 1)
            {
                if(debug)
                {
                    pt = new ParkingTicket("2018-09-29 8:47");
                }
                else
                {
                    pt = new ParkingTicket();
                }
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

    private String waitForInput()
    {
        String rString = new String();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            rString = br.readLine();
        } catch (IOException e) {
            System.out.println("Exception thrown in waitForInput");
            System.out.println(e.getMessage());
        }

        return rString;
    }

    @Override
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
