package org.example.task1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GroundControlCoordinator implements GroundServiceMediator {
    private CleaningService cleaningService;
    private CateringService cateringService;
    private BaggageService baggageService;
    private TowingService towingService;
    private RefuelingService refuelingService;

    private boolean boardingInProgress;
    private boolean cleaningCompleted;
    private boolean baggageLoaded;

    public void setCleaningService(CleaningService cleaningService) {
        this.cleaningService = cleaningService;
    }

    public void setCateringService(CateringService cateringService) {
        this.cateringService = cateringService;
    }

    public void setBaggageService(BaggageService baggageService) {
        this.baggageService = baggageService;
    }

    public void setTowingService(TowingService towingService) {
        this.towingService = towingService;
    }

    public void setRefuelingService(RefuelingService refuelingService) {
        this.refuelingService = refuelingService;
    }

    public void setBoardingInProgress(boolean boardingInProgress) {
        this.boardingInProgress = boardingInProgress;
        log("Статус посадки пасажирів змінено: " + (boardingInProgress ? "ТРИВАЄ ПОСАДКА" : "ПОСАДКУ ЗАВЕРШЕНО") + ".");
    }

    @Override
    public void notify(GroundService sender, String event) {
        String senderName = sender.getClass().getSimpleName();

        switch (event) {
            case "CLEANING_COMPLETED" -> {
                cleaningCompleted = true;
                log("Отримано подію '" + event + "' від " + senderName + ". Ініціюю запуск CateringService.");
                if (cateringService != null) {
                    cateringService.startCatering();
                }
            }
            case "BAGGAGE_LOADED" -> {
                baggageLoaded = true;
                log("Отримано подію '" + event + "' від " + senderName + ". Надаю дозвіл для TowingService.");
                if (towingService != null) {
                    towingService.allowTowing();
                }
            }
            case "REFUELING_REQUESTED" -> {
                if (boardingInProgress) {
                    log("Отримано подію '" + event + "' від " + senderName + ". Відмовлено: триває посадка пасажирів.");
                    if (refuelingService != null) {
                        refuelingService.postponeRefueling("триває посадка пасажирів");
                    }
                } else {
                    log("Отримано подію '" + event + "' від " + senderName + ". Посадка не триває — дозволяю заправку.");
                    if (refuelingService != null) {
                        refuelingService.startRefueling();
                    }
                }
            }
            default -> log("Отримано невідому подію '" + event + "' від " + senderName + ".");
        }
    }

    private void log(String message) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] [GroundControlCoordinator] %s%n", time, message);
    }
}