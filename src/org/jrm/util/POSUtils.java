package org.jrm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class model providing static method useful for all POS systems.
 * @author Jared Mallas
 * @version 1.0
 */
public class POSUtils {

    /**
     * Wait for and capture user input
     * @return String representation of what the user typed
     */
    public static String waitForInput()
    {
        String rString = new String();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            rString = br.readLine();
        } catch (IOException e) {
            System.out.println("Exception thrown in waitForInput");
            System.out.println(e.getMessage());
        }

        return rString;
    }
}
