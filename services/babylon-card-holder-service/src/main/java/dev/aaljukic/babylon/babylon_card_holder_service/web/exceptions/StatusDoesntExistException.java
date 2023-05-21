package dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions;

public class StatusDoesntExistException extends RuntimeException {
    public StatusDoesntExistException(String message) {
        super(message);
    }
}