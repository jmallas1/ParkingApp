package org.jrm;

import org.jrm.data.garage.Garage;
import org.jrm.pos.POSExit;

/**
 * Class model for a garage exit system
 * @author Jared Mallas
 * @version 1.0
 */
public class GarageExit
{
    public static void main(String[] args)
    {
        Garage gar = new Garage("Jared's Garage");

        POSExit pe = new POSExit(gar);

        pe.startUp();
    }
}
