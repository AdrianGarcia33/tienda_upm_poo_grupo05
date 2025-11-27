package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a meeting event.
 * [cite_start]Meetings require a minimum planning time of 12 hours[cite: 363].
 */
public class Meeting extends Events {
    private static final int PLANNING_TIME_HOURS = 12;

    /**
     * Constructs a new Meeting.
     * @param id Unique identifier.
     * @param name Meeting name.
     * @param basePrice Price per person.
     * @param expirationDate Date of the meeting.
     * @param maxParticipants Maximum number of attendees.
     */
    public Meeting(int id, String name, float basePrice, LocalDate expirationDate, int maxParticipants) {
        super(expirationDate, id, name, basePrice, maxParticipants);
    }

    /**
     * Copy constructor. Creates a new Meeting from an existing one.
     * @param other The meeting object to copy.
     */
    public Meeting(Meeting other){
        super(other.expirationDate, other.id, other.name, other.basePrice, other.maxParticipants);
    }

    /**
     * Checks if the meeting date allows for the required 12-hour planning period.
     * @return true if the date is valid, false otherwise.
     */
    @Override
    public boolean isTemporallyValid() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minimumDateTime = now.plusHours(PLANNING_TIME_HOURS);
        LocalDate planningDate = minimumDateTime.toLocalDate();
        return !this.expirationDate.isBefore(planningDate);

    }

    /**
     * Returns the string representation of the meeting.
     */
    @Override
    public String toString(){
        return "{class:Reuniones, id:" + id + ", name:'" + name +
                "', pricePerPerson:" + basePrice +
                ", expiration:" + expirationDate +
                ", maxParticipants:" + maxParticipants + "}";
    }
}