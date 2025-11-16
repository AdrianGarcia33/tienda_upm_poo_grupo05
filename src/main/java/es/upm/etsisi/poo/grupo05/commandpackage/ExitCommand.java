package es.upm.etsisi.poo.grupo05.commandpackage;

public class ExitCommand extends Command{

    public ExitCommand(String name) {
        super(name);
    }

    @Override
    public boolean apply(String[] args) {
        return true; //El unico return true para parar el bucle
    }
}
