package dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions;

public class CardHolderConflictException extends RuntimeException {
    public CardHolderConflictException(String message) {
        super(message);
    }
}
