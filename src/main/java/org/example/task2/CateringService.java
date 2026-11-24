package org.example.task2;

public class CateringService implements FlightObserver {
    @Override
    public void update(String time, String flightNumber, String eventDescription) {
        System.out.println("[" + time + "] [Кейтеринг] Зміна у графіку доставки на " + flightNumber + " через: " + eventDescription);
    }
}