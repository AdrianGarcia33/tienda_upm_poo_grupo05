package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Meeting extends Events {
    private static final int PLANNING_TIME_HOURS = 12;

    public Meeting(int id, String name, float basePrice, LocalDate expirationDate){
        super(expirationDate, id, name, basePrice);
    }
    @Override
    public boolean isTemporallyValid() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minimumDateTime = now.plusHours(PLANNING_TIME_HOURS);
        LocalDate planningDate = minimumDateTime.toLocalDate();
        return !this.expirationDate.isBefore(planningDate);

    }

    @Override
    public String toString(){
        return "{class:Reuniones, id:" + id + ", name:'" + name +
                "', pricePerPerson:" + basePrice +
                ", expiration:" + expirationDate +
                ", maxParticipants:" + MAX_PARTICIPANTS + "}";
    }
}