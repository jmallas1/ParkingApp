package org.jrm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class POSUtils {
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
