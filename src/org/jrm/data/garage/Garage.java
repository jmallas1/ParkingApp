package org.jrm.data.garage;

import org.jrm.data.ticket.ParkingTicket;
import org.jrm.io.FileInput;
import org.jrm.io.FileOutput;

import java.util.HashMap;

public class Garage
{
    private Integer occupancy = 0;
    private Integer capacity;
    private String name;
    private String dataFileName;

    private HashMap<String, ParkingTicket> tickets;

    public Garage(Integer capacity, String name)
    {
        this.capacity = capacity;
        this.name = name;
        this.dataFileName = genDataFileName();
        this.tickets = new HashMap<String, ParkingTicket>();
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
        FileOutput fo = new FileOutput("tickets-" + this.dataFileName + ".dat");
        String records = new String();

        // persist all tickets to a file
        for (String key : tickets.keySet())
        {
            records += tickets.get(key).toString() + "\n";
        }

        fo.writeFile(records.trim());
    }

    public void loadTickets()
    {
        String line;
        String[] workingArray = new String[1];
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

    /* Getters and setters */

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

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
