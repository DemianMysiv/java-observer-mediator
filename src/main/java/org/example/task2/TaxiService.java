package org.example.task2;

public class TaxiService implements FlightObserver {
    @Override
    public void update(String time, String flightNumber, String eventDescription) {
        boolean isDelay = eventDescription.contains("затримано");
        boolean isGateChange = eventDescription.contains("Змінено вихід");

        if (isDelay) {
            System.out.println("[" + time + "] [Таксі-сервіс] Водіям надіслано новий час подачі авто для пасажирів " + flightNumber);
        }

        if (isGateChange) {
            System.out.println("[" + time + "] [Таксі-сервіс] Водіїв перенаправлено до іншого терміналу для " + flightNumber);
        }
    }
}