package es.upm.etsisi.poo.grupo05;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Main, whose purpose is to read and execute commands
 */
public class App {
    public static void main(String[] args) {
        Handler handler = new Handler(200);
        handler.initialize();
        handler.start(args);
    }
}



