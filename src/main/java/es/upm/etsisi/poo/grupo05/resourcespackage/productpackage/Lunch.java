package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.time.LocalDate;

/**
 * Represents a lunch event.
 * Lunches require a minimum planning time of 3 days.
 */
public class Lunch extends Events {
    private static final int PLANNING_TIME_DAYS = 3;

    /**
     * Constructs a new Lunch event.
     * @param id Unique identifier.
     * @param name Name of the lunch.
     * @param basePrice Price per person.
     * @param expirationDate Date of the event.
     * @param maxParticipants Maximum number of attendees.
     */
    public Lunch(int id, String name, float basePrice, LocalDate expirationDate, int maxParticipants) {
        super(expirationDate, id, name, basePrice, maxParticipants);
    }

    /**
     * Copy constructor. Creates a new Lunch from an existing one.
     * @param other The lunch object to copy.
     */
    public Lunch(Lunch other){
        super(other.expirationDate, other.id, other.name, other.basePrice, other.maxParticipants);
    }

    /**
     * Checks if the event date allows for the required 3-day planning period.
     * @return true if the date is valid, false otherwise.
     */
    @Override
    public boolean isTemporallyValid() {
        LocalDate now = LocalDate.now();
        LocalDate minimumDate = now.plusDays(PLANNING_TIME_DAYS);
        return !this.expirationDate.isBefore(minimumDate);
    }

    /**
     * Returns the string representation of the lunch event.
     */
    @Override
    public String toString() {
        return "{class:Food" + super.getEventDetails();
    }
}
