package org.example.task1;

public class Task1Demo {
    public static void run() {
        GroundControlCoordinator coordinator = new GroundControlCoordinator();

        CleaningService cleaningService = new CleaningService(coordinator);
        CateringService cateringService = new CateringService(coordinator);
        BaggageService baggageService = new BaggageService(coordinator);
        TowingService towingService = new TowingService(coordinator);
        RefuelingService refuelingService = new RefuelingService(coordinator);

        coordinator.setCleaningService(cleaningService);
        coordinator.setCateringService(cateringService);
        coordinator.setBaggageService(baggageService);
        coordinator.setTowingService(towingService);
        coordinator.setRefuelingService(refuelingService);

        System.out.println("=== СЦЕНАРІЙ 1: Завершення прибирання -> запуск кейтерингу ===");
        cleaningService.finishCleaning();

        System.out.println("\n=== СЦЕНАРІЙ 2: Завершення завантаження багажу -> дозвіл на буксирування ===");
        baggageService.finishLoading();

        System.out.println("\n=== СЦЕНАРІЙ 3: Спроба заправки під час посадки пасажирів ===");
        coordinator.setBoardingInProgress(true);
        refuelingService.requestRefueling();
    }
}