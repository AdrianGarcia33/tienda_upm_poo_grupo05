package es.upm.etsisi.poo.grupo05.productpackage;

import java.time.LocalDate;

public abstract class Events extends Product {
    protected LocalDate expirationDate;
    protected static final int MAX_PARTICIPANTS = 100;

    public Events(LocalDate expirationDate, int id, String name, float basePrice) {
        super(id,name,basePrice);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getMaxParticipants() {
        return MAX_PARTICIPANTS;
    }

    //Tambien se puede comprobar amount antes de llamar y cambiarlo
    @Override
    public float getTotalPrice(int amount) {
        return basePrice * amount;
    }

    @Override
    public abstract boolean isTemporallyValid();

    @Override
    public abstract String toString();
}
