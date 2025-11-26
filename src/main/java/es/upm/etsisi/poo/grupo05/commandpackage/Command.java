package es.upm.etsisi.poo.grupo05.commandpackage;

/**
 * Abstract base class representing a generic command.
 * This class provides the structure for defining commands with a name
 * and an abstract method to execute the command logic.
 */
public abstract class Command {
    private String name ;

    /**
     * Constructor to initialize a Command object with a name.
     * @param name The name of the command.
     */
    public Command (String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the command.
     * @return The name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the command.
     * @param name The new name of the command.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Abstract method to define the main logic of the command.
     * If this is a generic superclass, it will search for the matching command.
     * If this is a final class, it will contain the logic for executing the command.
     *
     * @param args The arguments provided for the command.
     * @return A boolean indicating whether the command execution should stop further processing.
     */
    public abstract boolean apply(String[] args);
}
