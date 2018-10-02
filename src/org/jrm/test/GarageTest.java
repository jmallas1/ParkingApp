package org.jrm.test;

import org.jrm.data.garage.Garage;
import org.jrm.data.transaction.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GarageTest {

    Garage ga;
    ArrayList<Transaction> al;

    @BeforeEach
    void setUp()
    {
        ga = new Garage("Jared's Garage");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void genDailyReport()
    {
        al = new ArrayList<Transaction>();
        al.add(new Transaction("LOST", "txn", 25f));
        al.add(new Transaction("xxx-xxx-xxx", "txn", 12f));
        al.add(new Transaction("yyy-yyy-yyy", "txn", 5f));
        al.add(new Transaction("LOST", "txn", 25f));
        al.add(new Transaction("zzz-zzz-zzz", "txn", 6f));
        ga.setLedger(al);

        String report = "Jared's Garage\n" +
                "==============\n" +
                "Activity to date:\n" +
                "\n" +
                "$23.00 was collected for 3 checkins\n" +
                "\n" +
                "$50.00 was collected for 2 lost tickets\n" +
                "\n" +
                "\n" +
                "$73.00 was collected overall.\n";

        assertEquals(report, ga.genDailyReport(), "Report generated as expected");
    }
}