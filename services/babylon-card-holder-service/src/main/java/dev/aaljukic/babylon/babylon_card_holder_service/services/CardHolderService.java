package dev.aaljukic.babylon.babylon_card_holder_service.services;

import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.card_holder.*;

public interface CardHolderService {
    RegisterResponseCardHolderDTO createCardHolderAccount(RegisterRequestCardHolderDTO requestCardHolderDTO);

    FindResponseCardHolderDTO readCardHolderAccount(FindRequestCardHolderDTO requestCardHolderDTO);

    UpdateResponseCardHolderDTO updateCardHolderAccount(UpdateRequestCardHolderDTO requestCardHolderDTO);

    DeleteResponseCardHolderDTO deleteCardHolderAccount(DeleteRequestCardHolderDTO requestCardHolderDTO);
}
