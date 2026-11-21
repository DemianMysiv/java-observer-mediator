package org.example.task1;

public class BaggageService extends GroundService {
    public BaggageService(GroundServiceMediator mediator) {
        super(mediator, "Служба завантаження багажу");
    }

    public void finishLoading() {
        log("Завантаження багажу завершено. Сповіщення координатора.");
        notifyMediator("BAGGAGE_LOADED");
    }
}