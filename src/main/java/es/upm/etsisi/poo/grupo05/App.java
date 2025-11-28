package es.upm.etsisi.poo.grupo05;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Main, whose purpose is to initialize handler and connect it to the possible input file.
 */
public class App {
    public static void main(String[] args) {
        Handler handler = new Handler(200);
        handler.initialize();
        handler.start(args);
    }
}



