package org.jrm.test;

import org.jrm.io.FileOutput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileOutputTest {

    FileOutput fo = null;

    @BeforeEach
    void setUp() { fo = new FileOutput("./foTest.out"); }

    @AfterEach
    void tearDown() { new File("./foTest.out").delete(); }

    @DisplayName("You should be able to write a file")
    @Test
    void writeFile()
    {
        fo.writeFile("This is a file");
        assertTrue((new File("./foTest.out").exists()));
    }
}