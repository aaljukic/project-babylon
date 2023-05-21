package dev.aaljukic.babylon.babylon_card_holder_service.common.enums;

public enum CardHolderActivityStatus {
    ACTIVE(1L),
    INACTIVE(2L);

    public final Long id;

    private CardHolderActivityStatus(Long id) {
        this.id = id;
    }
}
