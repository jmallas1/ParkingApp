package org.jrm;

import org.jrm.data.garage.Garage;
import org.jrm.pos.POSEntry;

public class GarageEntry
{
    public static void main(String[] args)
    {
        Garage gar = new Garage("Jared's Garage");

        POSEntry pe = new POSEntry(gar);
    }
}
