package es.upm.etsisi.poo.grupo05;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reuniones extends Events {
    private static final int PLANNING_TIME_HOURS = 12;

    public Reuniones(String id, String name, float basePrice, LocalDate expirationDate){
        super(expirationDate, id, name, basePrice);
    }
    // TIENE UN ERROR
    @Override
    public boolean isTemporallyValid() {
        LocalDate now = LocalDate.now();
        LocalDateTime minimumDateTime = now.atStartOfDay().plusHours(PLANNING_TIME_HOURS);
        return !this.expirationDate.isBefore(minimumDateTime);

    }

    @Override
    public String toString(){
        return "{class:Reuniones, id:" + id + ", name:'" + name +
                "', pricePerPerson:" + basePrice +
                ", expiration:" + expirationDate +
                ", maxParticipants:" + MAX_PARTICIPANTS + "}";
    }
}