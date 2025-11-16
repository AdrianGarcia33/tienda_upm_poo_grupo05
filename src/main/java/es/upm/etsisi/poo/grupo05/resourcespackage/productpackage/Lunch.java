package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.time.LocalDate;

public class Lunch extends Events {
    private static final int PLANNING_TIME_DAYS = 3;

    public Lunch(int id, String name, float basePrice, LocalDate expirationDate) {
        super(expirationDate, id, name, basePrice);
    }

    @Override
    public boolean isTemporallyValid() {
        LocalDate now = LocalDate.now();
        LocalDate minimumDate = now.plusDays(PLANNING_TIME_DAYS);
        return !this.expirationDate.isBefore(minimumDate);
    }
    // Cuando saquen el ejemplo ajustamos el toString
    @Override
    public String toString() {
        return "{class:Comida, id:" + id + ", name:'" + name +
                "', pricePerPerson:" + basePrice +
                ", expiration:" + expirationDate +
                ", maxParticipants:" + MAX_PARTICIPANTS + "}";
    }
}
