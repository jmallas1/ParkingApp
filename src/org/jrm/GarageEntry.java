package org.jrm;

import org.jrm.data.garage.Garage;
import org.jrm.pos.POSEntry;

import java.util.Map;

public class GarageEntry
{
    private Map tickets;

    public static void main(String[] args)
    {
        Garage gar = new Garage(500, "Jared's Garage");

        POSEntry pe = new POSEntry(gar);
    }
}
