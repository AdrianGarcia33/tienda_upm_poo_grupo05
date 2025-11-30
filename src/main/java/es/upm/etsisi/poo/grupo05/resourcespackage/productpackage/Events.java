package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.time.LocalDate;
import java.util.Locale;

/**
 * Abstract base class for event products (e.g., Lunch, Meeting).
 * Events have an expiration date and a participant limit.
 */
public abstract class Events extends Product {
    protected LocalDate expirationDate;
    protected int maxParticipants;
    protected static final int LIMIT_PARTICIPANTS = 100;
    protected int actualParticipants = 0;

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

    public int getActualParticipants() {
        return actualParticipants;
    }

    public void setActualParticipants(int actualParticipants) {
        this.actualParticipants = actualParticipants;
    }

    /**
     * Create the String to return looking for the actual participants
     * @return
     */
    protected String getEventDetails() {

        float displayPrice = (actualParticipants == 0) ? 0.0f : getTotalPrice(actualParticipants);

        StringBuilder sb = new StringBuilder();
        sb.append(", id:").append(id)
                .append(", name:'").append(name).append("'")
                .append(", price:").append(String.format(Locale.US, "%.1f", displayPrice))
                .append(", date of Event:").append(expirationDate)
                .append(", max people allowed:").append(maxParticipants);

        if (actualParticipants > 0) {
            sb.append(", actual people in event:").append(actualParticipants);
        }

        sb.append("}");
        return sb.toString();
    }

    @Override
    public abstract String toString();
}
