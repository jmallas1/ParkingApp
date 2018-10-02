package org.jrm.data.garage;

import org.jrm.data.ticket.ParkingTicket;
import org.jrm.data.transaction.Transaction;
import org.jrm.io.FileInput;
import org.jrm.io.FileOutput;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class model for a garage
 * @author Jared Mallas
 * @version 1.0
 */
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

    /**
     * Generate a string to be used to create data files (ex: ledger and ticket roster)
     * @return String representation of this.name with all special characters and whitespace removed
     */
    private String genDataFileName()
    {
        String rString;
        rString = this.name.toLowerCase().replaceAll("\\W", "");
        return rString;
    }

    /**
     * Generate a string of the "daily report" of garage specifics for printing.
     * @return String containing the daily report for display
     */
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

    /**
     * Add a ticket to the running ticket 'roster' in memory
     * THIS IN-MEMORY ROSTER MAY BE PERSISTED TO DISK AT CERTAIN TIMES.
     * @param pt Instance of org.jrm.ticket.ParkingTicket
     */
    public void pushTicket(ParkingTicket pt)
    {
        this.tickets.put(pt.getTicketID(), pt);
    }

    /**
     * Remove a given ticket from the running ticket 'roster' in memory.
     * THIS IN-MEMORY ROSTER MAY BE PERSISTED TO DISK AT CERTAIN TIMES.
     * @param pt Instance of org.jrm.ticket.ParkingTicket
     */
    public void popTicket(ParkingTicket pt)
    {
        this.tickets.remove(pt.getTicketID());
    }

    /**
     * Writes in memory ticket 'roster' to data file, loads data file representation
     * of the garage 'ledger' into memory and generates a report based on ledger
     */
    public void closeGarage()
    {
        saveTickets();
        loadLedger();
        System.out.println(genDailyReport());
    }

    /**
     * Loads data file ticket 'roster' into memory if file exists
     */
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

    /**
     * Writes in-memory ticket 'roster' to a data file
     */
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

    /**
     * Adds a transaction to in memory ledger then persists ledger to data file
     * @param txn instance of org.jrm.transaction.Transaction
     */
    public void addToLedger(Transaction txn)
    {
        ledger.add(txn);
        saveLedger();
    }

    /**
     * Persists in-memory ledger to data file
     */
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

    /**
     * Loads in-memory ledger from data fiel
     */
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

    public void setLedger(ArrayList<Transaction> al)
    {
        ledger = al;
    }
}
