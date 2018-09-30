package org.jrm;

import org.jrm.data.garage.Garage;
import org.jrm.pos.POSExit;

public class GarageExit
{
    public static void main(String[] args)
    {
        Garage gar = new Garage(500, "Jared's Garage");

        POSExit pe = new POSExit(gar);
    }
}
