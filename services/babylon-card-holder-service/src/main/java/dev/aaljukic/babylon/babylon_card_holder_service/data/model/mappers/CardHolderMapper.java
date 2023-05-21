package dev.aaljukic.babylon.babylon_card_holder_service.data.model.mappers;

import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.CardHolder;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.Status;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.card_holder.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardHolderMapper {

    @Mapping(target = "status.id", source = "statusId")
    CardHolder registerRequestToCardHolder(RegisterRequestCardHolderDTO requestCardHolderDTO);

    @Mapping(target = "statusDTO.id", source = "status.id")
    @Mapping(target = "statusDTO.description", source = "status.description")
    RegisterResponseCardHolderDTO cardHolderToRegisterResponseDto(CardHolder cardHolder, Status status);

    @Mapping(target = "statusDTO.id", source = "status.id")
    @Mapping(target = "statusDTO.description", source = "status.description")
    FindResponseCardHolderDTO cardHolderToFindResponseDto(CardHolder cardHolder, Status status);

    @Mapping(target = "statusDTO.id", source = "status.id")
    @Mapping(target = "statusDTO.description", source = "status.description")
    UpdateResponseCardHolderDTO cardHolderToUpdateResponseDto(CardHolder cardHolder, Status status);

    @Mapping(target = "statusDTO.id", source = "status.id")
    @Mapping(target = "statusDTO.description", source = "status.description")
    DeleteResponseCardHolderDTO cardHolderToDeleteResponseDto(CardHolder cardHolder, Status status);

    @Mapping(target = "firstName", source = "cardHolder.firstName")
    @Mapping(target = "lastName", source = "cardHolder.lastName")
    @Mapping(target = "oib", source = "cardHolder.oib")
    @Mapping(target = "status", source = "cardHolder.status")
    CardHolder updateToNewCardHolder(CardHolder cardHolder);
}
