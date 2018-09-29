package org.jrm;

import org.jrm.data.ticket.ParkingTicket;

public class Main {

    public static void main(String[] args) {
        ParkingTicket pt1 = new ParkingTicket();
        String sPT1 = pt1.toString();
        ParkingTicket pt2 = new ParkingTicket("XXXX-XXXX-XXXX-XXXX", "2018-09-29 02:45");
        String xPT2 = pt2.toString();

        System.out.println("OYE!!");
    }
}
