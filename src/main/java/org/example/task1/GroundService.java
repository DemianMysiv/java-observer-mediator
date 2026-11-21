package org.example.task1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class GroundService {
    protected final GroundServiceMediator mediator;
    protected final String serviceName;

    protected GroundService(GroundServiceMediator mediator, String serviceName) {
        this.mediator = mediator;
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    protected void notifyMediator(String event) {
        mediator.notify(this, event);
    }

    protected void log(String message) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] [%s] %s%n", time, this.getClass().getSimpleName(), message);
    }
}