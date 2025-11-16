package es.upm.etsisi.poo.grupo05.commandpackage;

public class EchoCommand extends Command{
    public EchoCommand (String name) {
        super(name);
    }

    @Override
    public boolean apply(String[] args) {
        System.out.println(args[0]);
        return false;
    }
}
