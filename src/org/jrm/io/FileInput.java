package org.jrm.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class model for FileInput
 * @author Jared R Mallas
 * @version 1.0
 */
public class FileInput
{
    public String filePath;
    private BufferedReader in = null;

    /**
     * Constructor for FileInput
     * @param filePath String containing a full or relative path to a readable file
     */
    public FileInput(String filePath)
    {
        this.filePath = filePath;

        try
        {
            in = new BufferedReader(new FileReader(filePath));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File: " + filePath + " not found");
            this.filePath = null;
        }
    }

    /**
     * Method to read an entire file into a String
     * @return String containing the contents of a file
     */
    public String readFile()
    {
        String rString = new String();
        String line;
        try
        {
            while ((line = in.readLine()) != null)
            {
                rString += line;
                rString += '\n';
            }
            // remove trailing '\n'
            rString = rString.substring(0, rString.length() -1);
        }
        catch (Exception e)
        {
            System.out.println("File Read Error: " + filePath + " " + e);
        }
        return rString;
    }

    /**
     * Method to fetch a single line from a file
     * @return string containing the next line
     */
    public String readLine()
    {
        String rString = null;

        try
        {
            rString = in.readLine();
        }
        catch (IOException e)
        {
            System.out.println("File Read Error: " + filePath + " " + e);
        }
        return rString;
    }

    /**
     * Close file that is opened by constructor
     */
    public void fileClose() {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}