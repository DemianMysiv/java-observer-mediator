package org.example.task1;

public class CleaningService extends GroundService {
    public CleaningService(GroundServiceMediator mediator) {
        super(mediator, "Служба прибирання салону");
    }

    public void finishCleaning() {
        log("Прибирання салону завершено. Сповіщення координатора.");
        notifyMediator("CLEANING_COMPLETED");
    }
}