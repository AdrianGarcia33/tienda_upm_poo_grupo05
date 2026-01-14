package es.upm.etsisi.poo.grupo05;

import org.junit.jupiter.api.Test;
import java.io.*;

import org.junit.jupiter.api.Test;
import java.io.*;

public class AppTest {
    @Test
    public void testInputFromFile() throws Exception {
        InputStream originalIn = System.in;
        try (FileInputStream fis = new FileInputStream("input3.txt")){
            System.setIn(fis);
            App.main(new String[]{});
        } finally {
            System.setIn(originalIn);
        }
    }
}
