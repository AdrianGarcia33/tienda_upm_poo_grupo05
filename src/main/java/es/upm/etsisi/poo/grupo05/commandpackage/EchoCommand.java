package es.upm.etsisi.poo.grupo05.commandpackage;

import java.util.Arrays;

public class EchoCommand extends Command{
    public EchoCommand (String name) {
        super(name);
    }

    @Override
    public boolean apply(String[] args) {
        System.out.println(String.join(" ", args).trim());
        return false;
    }
}
