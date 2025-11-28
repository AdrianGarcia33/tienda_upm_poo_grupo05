package es.upm.etsisi.poo.grupo05.commandpackage;

/**
 * The class made to exit the loop of commands
 */
public class ExitCommand extends Command{

    public ExitCommand(String name) {
        super(name);
    }

    @Override
    public boolean apply(String[] args) {
        return true; //El unico return true para parar el bucle
    }
}
