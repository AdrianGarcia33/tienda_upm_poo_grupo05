package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.time.LocalDate;

/**
 * Abstract base class for event products (e.g., Lunch, Meeting).
 * Events have an expiration date and a participant limit.
 */
public abstract class Events extends Product {
    protected LocalDate expirationDate;
    protected int maxParticipants;
    protected static final int LIMIT_PARTICIPANTS = 100;

    /**
     * Constructs a new Event.
     * @param expirationDate Date of the event.
     * @param id Unique identifier.
     * @param name Name of the event.
     * @param basePrice Price per person.
     * @param maxParticipants Maximum number of attendees for this event.
     */
    public Events(LocalDate expirationDate, int id, String name, float basePrice, int maxParticipants) {
        super(id,name,basePrice);
        this.expirationDate = expirationDate;
        this.maxParticipants = maxParticipants;
    }

    /**
     * Getters, setters and methods to implement.
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public static int getLimitParticipants() {
        return LIMIT_PARTICIPANTS;
    }

    @Override
    public float getTotalPrice(int amount) {
        return basePrice * amount;
    }

    @Override
    public abstract boolean isTemporallyValid();

    @Override
    public abstract String toString();
}
