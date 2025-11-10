package es.upm.etsisi.poo.grupo05.productpackage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

public class Reuniones extends Events {
    private static final int PLANNING_TIME_HOURS = 12;

    public Reuniones(int id, String name, float basePrice, LocalDate expirationDate){
        super(expirationDate, id, name, basePrice);
    }
    // TIENE UN ERROR
    @Override
    public boolean isTemporallyValid() {
        LocalDateTime now = LocalDate.now().atStartOfDay();
        LocalDateTime minimumDateTime = now.plusHours(PLANNING_TIME_HOURS);
        return !this.expirationDate.isBefore(ChronoLocalDate.from(minimumDateTime));

    }

    @Override
    public String toString(){
        return "{class:Reuniones, id:" + id + ", name:'" + name +
                "', pricePerPerson:" + basePrice +
                ", expiration:" + expirationDate +
                ", maxParticipants:" + MAX_PARTICIPANTS + "}";
    }
}