package org.jrm.pos;

import org.jrm.data.garage.Garage;
import org.jrm.data.ticket.ParkingTicket;
import org.jrm.data.transaction.Transaction;
import org.jrm.util.POSUtils;
import org.jrm.util.TimeUtils;

import java.util.Date;
import java.util.HashMap;

/**
 * Class model for a "Point of Sale" system for exiting a garage
 * @author Jared Mallas
 * @version 1.0
 */
public class POSExit
{
    private Boolean debug = true;
    private Garage location;
    private Boolean done = false;
    private ParkingTicket pt;
    private HashMap<String, String> billDetails;
    private Date outTime;
    Date dt1 = TimeUtils.stringDateToDate("2018-10-03 13:00");
    Date dt2 = TimeUtils.stringDateToDate("2018-10-03 23:00");

    String userChoice;

    public POSExit(Garage location)
    {
        this.location = location;
    }

    /**
     * Generate a final bill / receipt for parking
     * @param details Hash map containing relevant details like car ID, time in, time out, amount charged
     * @return String representation of bill / receipt for display
     */
    public String generateBill(HashMap<String, String> details)
    {
        String rString = new String();

        rString += "Receipt for vehicle: ";

        if(details.containsKey("in"))
        {
            rString += details.get("id") + "\n";
            rString += TimeUtils.getHours(TimeUtils.stringDateToDate(details.get("in")), TimeUtils.stringDateToDate(details.get("out"))).toString();
            rString += " hours parked: \n";
            rString += details.get("in") + " - " + details.get("out") + "\n";
        }
        else
        {
            rString += "\n\nLost Ticket:\n";
        }

        rString += "$" + details.get("charge");

        rString += "\n\n\n";

        return rString;
    }

    /**
     * Display a common 'banner" for POS system
     */
    public void displayBanner()
    {
        for (int i = 1; i < 100; i++)
        {
            System.out.print("\n");
        }

        System.out.println("Thank you for visiting " + this.location.getName());
        System.out.println("=====================================");

    }

    private HashMap doTicketExit()
    {
        HashMap bd = new HashMap();
        System.out.println("Enter Ticket ID");
        System.out.println("\n => ");
        userChoice = POSUtils.waitForInput();

        if(location.getTickets().containsKey(userChoice))
        {
            pt = location.getTickets().get(userChoice);
            location.loadTickets();
            location.popTicket(pt);
            location.saveTickets();
            bd.put("id", pt.getTicketID());
            bd.put("charge", String.format("%.02f", pt.getCharge(outTime)));
            bd.put("in", TimeUtils.dateToString(pt.getTimeIn()));
            bd.put("out", TimeUtils.dateToString(outTime));
        }
        else
        {
            displayBanner();
            System.out.println("Unable to locate ticket.");
            System.out.println("Please check the number and try again.");
            bd.put("charge", "nil");
        }

        return bd;
    }

    private HashMap doLostTicket()
    {
        HashMap bd = new HashMap();

        bd.put("charge", "25.00");
        bd.put("id", "LOST");

        return bd;
    }

    /**
     * Start up simple POS exit program
     */
    public void startUp()
    {
        while(!done)
        {
            displayBanner();
            System.out.println("1 - Leave / receive bill");
            System.out.println("\n");
            System.out.println("2 - Lost ticket");
            System.out.println("\n");
            System.out.printf("=> ");

            userChoice = POSUtils.waitForInput();

            displayBanner();

            if(debug)
            {
                outTime = TimeUtils.stringDateToDate(TimeUtils.randomTimeString(dt1, dt2));
            }
            else
            {
                outTime = new Date();
            }

            switch (Integer.parseInt(userChoice))
            {
                case 1:
                    billDetails = doTicketExit();
                    break;
                case 2:
                    billDetails = doLostTicket();
                    break;
                case 3:
                    location.closeGarage();
                    System.exit(0);
                    break;
            }

            if(billDetails.get("charge") != "nil")
            {
                location.addToLedger(new Transaction(billDetails.get("id"), "txn", Float.parseFloat(billDetails.get("charge"))));
                displayBanner();
                System.out.println(generateBill(billDetails));
            }

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
