package org.example.task1;

public class CateringService extends GroundService {
    public CateringService(GroundServiceMediator mediator) {
        super(mediator, "Служба кейтерингу");
    }

    public void startCatering() {
        log("Отримано команду від координатора: розпочато завантаження бортового харчування.");
    }
}