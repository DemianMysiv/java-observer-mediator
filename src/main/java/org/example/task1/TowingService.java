package org.example.task1;

public class TowingService extends GroundService {
    public TowingService(GroundServiceMediator mediator) {
        super(mediator, "Служба буксирування");
    }

    public void allowTowing() {
        log("Отримано дозвіл від координатора: буксирування борту дозволено.");
    }
}