package org.jrm.pos;

import org.jrm.data.garage.Garage;

public class POSExit implements POSMachine
{
    private Garage location;
    private Boolean done = false;
    String userChoice;

    public POSExit(Garage location)
    {
        this.location = location;

        displayBanner();

    }

    @Override
    public void displayBanner()
    {
        System.out.println("Thank you for visiting " + this.location.getName());
        System.out.println("=========================");
        System.out.println("\n");

        System.out.println("1 - Leave / receive bill");
        System.out.println("\n");
        System.out.println("2 - Lost ticket");
        System.out.println("\n");
        System.out.printf("=> ");
    }
}
