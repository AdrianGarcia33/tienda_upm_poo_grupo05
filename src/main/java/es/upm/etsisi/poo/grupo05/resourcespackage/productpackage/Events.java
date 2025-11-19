package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.time.LocalDate;

public abstract class Events extends Product {
    protected LocalDate expirationDate;
    protected int maxParticipants;
    protected static final int LIMIT_PARTICIPANTS = 100;

    public Events(LocalDate expirationDate, int id, String name, float basePrice, int maxParticipants) {
        super(id,name,basePrice);
        this.expirationDate = expirationDate;
        this.maxParticipants = maxParticipants;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public static int getLimitParticipants() {
        return LIMIT_PARTICIPANTS;
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
