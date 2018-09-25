package org.jrm.test;

import org.jrm.io.FileInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileInputTest {

    FileInput fi;

    @BeforeEach
    void setUp()
    {
        fi = new FileInput("./src/org/jrm/test/FileInputTest.java");
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("You should be able to read a file")
    @Test
    void readFile() { assertEquals("package org.jrm.test;", fi.readFile().substring(0,21), "Substring 21 of file should contain import statement"); }

    @DisplayName("You should be able to read the first line of a file")
    @Test
    void readLine() { assertEquals("package org.jrm.test;", fi.readLine(), "First line of file should contain import statement"); }
}