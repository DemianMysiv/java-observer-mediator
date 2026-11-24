package org.example.task2;

public class Task2Demo {
    public static void main(String[] args) {
        System.out.println("=== Демонстрація Observer (Завдання 2) ===\n");


        Flight flight = new Flight("PS-101");


        InformationBoard board = new InformationBoard();
        Passenger passenger = new Passenger("Іван");
        CateringService catering = new CateringService();
        TaxiService taxi = new TaxiService();


        flight.addObserver(board);
        flight.addObserver(passenger);
        flight.addObserver(catering);
        flight.delayFlight("18:45");




        flight.addObserver(taxi);
        flight.changeGate("F3");



        flight.removeObserver(catering);
        flight.startBoarding();
    }
}