package org.example.task2;

public class InformationBoard implements FlightObserver {
    @Override
    public void update(String time, String flightNumber, String eventDescription) {
        System.out.println("[" + time + "] [Табло] Оновлено рядок для " + flightNumber + ": " + eventDescription);
    }
}