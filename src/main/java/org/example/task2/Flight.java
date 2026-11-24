package org.example.task2;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    private String flightNumber;
    private String gate;
    private List<FlightObserver> observers;

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
        this.gate = "Не визначено";
        this.observers = new ArrayList<>();
    }


    public void addObserver(FlightObserver observer) {
        observers.add(observer);
    }


    public void removeObserver(FlightObserver observer) {
        observers.remove(observer);
    }


    private String getCurrentTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(formatter);
    }


    private void notifyObservers(String eventDescription) {
        String time = getCurrentTime();
        System.out.println("[" + time + "] [Рейс " + flightNumber + "] " + eventDescription);
        for (FlightObserver observer : observers) {
            observer.update(time, flightNumber, eventDescription);
        }
        System.out.println("--------------------------------------------------");
    }


    public void delayFlight(String newTime) {
        String message = "Рейс затримано. Очікуваний час вильоту: " + newTime;
        notifyObservers(message);
    }


    public void changeGate(String newGate) {
        this.gate = newGate;
        String message = "Змінено вихід на посадку на " + newGate;
        notifyObservers(message);
    }


    public void startBoarding() {
        String message = "Почато посадку на виході " + gate;
        notifyObservers(message);
    }
}