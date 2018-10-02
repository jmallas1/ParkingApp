package org.jrm;

import org.jrm.data.garage.Garage;
import org.jrm.pos.POSEntry;

/**
 * Class model for a Garage entry system
 * @author Jared Mallas
 * @version 1.0
 */
public class GarageEntry
{
    public static void main(String[] args)
    {
        Garage gar = new Garage("Jared's Garage");

        POSEntry pe = new POSEntry(gar);

        pe.startUp();
    }
}
