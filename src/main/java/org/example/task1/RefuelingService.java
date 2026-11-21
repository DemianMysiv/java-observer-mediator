package org.example.task1;

public class RefuelingService extends GroundService {
    public RefuelingService(GroundServiceMediator mediator) {
        super(mediator, "Служба заправки");
    }

    public void requestRefueling() {
        log("Запит до координатора на початок заправки борту.");
        notifyMediator("REFUELING_REQUESTED");
    }

    public void startRefueling() {
        log("Отримано дозвіл від координатора: розпочато заправку борту.");
    }

    public void postponeRefueling(String reason) {
        log("Заправку відкладено за рішенням координатора. Причина: " + reason + ".");
    }
}