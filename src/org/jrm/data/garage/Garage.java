package org.jrm.data.garage;

import org.jrm.data.ticket.ParkingTicket;
import org.jrm.data.transaction.Transaction;
import org.jrm.io.FileInput;
import org.jrm.io.FileOutput;

import java.util.ArrayList;
import java.util.HashMap;

public class Garage
{
    private String name;
    private String dataFileName;
    private ArrayList<Transaction> ledger;
    private Integer lostTickets = 0;

    private HashMap<String, ParkingTicket> tickets;

    public Garage(String name)
    {
        this.name = name;
        this.dataFileName = genDataFileName();
        this.tickets = new HashMap<String, ParkingTicket>();
        this.ledger = new ArrayList();
        loadTickets();
    }

    private String genDataFileName()
    {
        String rString;
        rString = this.name.toLowerCase().replaceAll("\\W", "");
        return rString;
    }

    public void pushTicket(ParkingTicket pt)
    {
        this.tickets.put(pt.getTicketID(), pt);
    }

    public void popTicket(ParkingTicket pt)
    {
        this.tickets.remove(pt.getTicketID());
    }

    public void closeGarage()
    {
        saveTickets();
        loadLedger();
        System.out.println(genDailyReport());
    }

    public void loadTickets()
    {
        String line;
        String[] workingArray;
        FileInput fi = new FileInput("tickets-" + this.dataFileName + ".dat");
        if(fi.filePath != null)
        {
            while ((line = fi.readLine()) != null)
            {
                workingArray = line.split(", ");
                pushTicket(new ParkingTicket(workingArray[0], workingArray[1]));

            }
        }
    }

    public void saveTickets()
    {
        FileOutput fo = new FileOutput("tickets-" + this.dataFileName + ".dat");
        String records = new String();

        // persist all tickets to a file
        for (String key : tickets.keySet())
        {
            records += tickets.get(key).toString() + "\n";
        }

        fo.writeFile(records.trim());
    }

    public void addToLedger(Transaction txn)
    {
        ledger.add(txn);
        saveLedger();
    }

    public void saveLedger()
    {
        FileOutput fo = new FileOutput("ledger-" + this.dataFileName + ".dat");
        String records = new String();

        for (Transaction tnx : ledger)
        {
            records += tnx.toString() + "\n";
        }

        fo.writeFile(records.trim());
    }

    public void loadLedger()
    {
        String line;
        String[] workingArray;

        FileInput fi = new FileInput("ledger-" + this.dataFileName + ".dat");
        if(fi.filePath != null)
        {
            while ((line = fi.readLine()) != null)
            {
                workingArray = line.split(", ");
                ledger.add(new Transaction(workingArray[0], workingArray[1], Float.parseFloat(workingArray[2])));
            }
        }
    }

    public String genDailyReport()
    {
        String rString = new String();
        Float totalReceipts = 0f;
        Float totalTickets = 0f;
        Float totalFee = 0f;
        Integer tickets = 0;
        Integer lost = 0;

        rString += this.name + "\n";
        for (int x = 1; x<=this.name.length(); x++)
        {
            rString += "=";
        }

        rString += "\nActivity to date:\n\n";

        for (Transaction txn : ledger)
        {
            totalReceipts += txn.getCharge();
            if(txn.getCustID().equals("LOST"))
            {
                lost++;
                totalFee+=txn.getCharge();
            }
            else
            {
                tickets++;
                totalTickets+=txn.getCharge();
            }
        }

        rString += "$" + String.format("%.02f", totalTickets) + " was collected for " + tickets + " checkins\n\n";
        rString += "$" + String.format("%.02f", totalFee) + " was collected for " + lost + " lost tickets\n\n\n";
        rString += "$" + String.format("%.02f", totalReceipts) + " was collected overall.\n";
        return rString;
    }

    /* Getters and setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ParkingTicket> getTickets() {
        return tickets;
    }

    public void setTickets(HashMap<String, ParkingTicket> tickets) {
        this.tickets = tickets;
    }
}
