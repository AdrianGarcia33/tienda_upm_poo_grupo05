package es.upm.etsisi.poo.grupo05.commandpackage;

public abstract class Command {
    private String name ;

    public Command (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean apply(String[] args);
}
