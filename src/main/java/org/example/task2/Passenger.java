package org.example.task2;

public class Passenger implements FlightObserver {
    private String name;

    public Passenger(String name) {
        this.name = name;
    }

    @Override
    public void update(String time, String flightNumber, String eventDescription) {
        System.out.println("[" + time + "] [Пасажир " + name + "] Отримано SMS: " + eventDescription);
    }
}