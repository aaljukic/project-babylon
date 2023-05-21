package dev.aaljukic.babylon.babylon_card_holder_service.services.impl;

import dev.aaljukic.babylon.babylon_card_holder_service.common.enums.CardHolderActivityStatus;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.CardHolder;
//import dev.aaljukic.babylon.babylon_card_holder_service.data.model.mappers.CardHolderMapper;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.Status;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.card_holder.*;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.mappers.CardHolderMapper;
import dev.aaljukic.babylon.babylon_card_holder_service.data.repository.CardHolderRepository;
import dev.aaljukic.babylon.babylon_card_holder_service.data.repository.StatusRepository;
import dev.aaljukic.babylon.babylon_card_holder_service.services.CardHolderService;
import dev.aaljukic.babylon.babylon_card_holder_service.services.FileGeneratorService;
import dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions.CardHolderConflictException;
import dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions.CardHolderNotFoundException;
import dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions.StatusDoesntExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CardHolderServiceImpl implements CardHolderService {

    private final CardHolderRepository cardHolderRepository;

    private final StatusRepository statusRepository;

    private final CardHolderMapper cardHolderMapper;

    private final FileGeneratorService fileGeneratorService;

    @Override
    public RegisterResponseCardHolderDTO createCardHolderAccount(RegisterRequestCardHolderDTO requestCardHolderDTO) {
        Optional<CardHolder> cardHolder = cardHolderRepository.findByOib(requestCardHolderDTO.getOib());

        if (cardHolder.isPresent()) {
            throw new CardHolderConflictException(
                    String.format("There is a conflict - Card Holder with %s OIB already exist", requestCardHolderDTO.getOib())
            );
        }

        Status status = statusRepository.findById(requestCardHolderDTO.getStatusId())
                .orElseThrow(() -> new StatusDoesntExistException("Status doesnt exist"));


        return cardHolderMapper.cardHolderToRegisterResponseDto(
                cardHolderRepository.save(
                        cardHolderMapper.registerRequestToCardHolder(requestCardHolderDTO)
                ),
                status
        );
    }

    @Override
    public FindResponseCardHolderDTO readCardHolderAccount(FindRequestCardHolderDTO requestCardHolderDTO) {
        CardHolder cardHolder = cardHolderRepository.findByOib(requestCardHolderDTO.getOib())
                .orElseThrow(() -> new CardHolderNotFoundException(
                        String.format("Card Holder with %s OIB is not found", requestCardHolderDTO.getOib())
                ));

        Status status = statusRepository.findById(cardHolder.getStatus().getId())
                .orElseThrow(() -> new StatusDoesntExistException("Status doesnt exist"));

        return cardHolderMapper.cardHolderToFindResponseDto(
                cardHolder,
                status
        );
    }

    @Override
    public UpdateResponseCardHolderDTO updateCardHolderAccount(UpdateRequestCardHolderDTO requestCardHolderDTO) {
        CardHolder cardHolder = cardHolderRepository.findByOib(requestCardHolderDTO.getOib())
                .orElseThrow(() -> new CardHolderNotFoundException(
                        String.format("Card Holder with %s OIB is not found", requestCardHolderDTO.getOib())
                ));

        Status status = statusRepository.findById(cardHolder.getStatus().getId())
                .orElseThrow(() -> new StatusDoesntExistException("Something went wrong: Status doesnt exist"));

        if (requestCardHolderDTO.getFirstName() != null) {
            cardHolder.setFirstName(requestCardHolderDTO.getFirstName());
        }
        if (requestCardHolderDTO.getLastName() != null) {
            cardHolder.setLastName(requestCardHolderDTO.getLastName());
        }

        return cardHolderMapper.cardHolderToUpdateResponseDto(
                cardHolderRepository.save(
                        cardHolderMapper.updateToNewCardHolder(cardHolder)
                ),
                status
        );
    }

    @Override
    public DeleteResponseCardHolderDTO deleteCardHolderAccount(DeleteRequestCardHolderDTO requestCardHolderDTO) {
        CardHolder cardHolder = cardHolderRepository.findByOib(requestCardHolderDTO.getOib())
                .orElseThrow(() -> new CardHolderNotFoundException(
                        String.format("Card Holder with %s OIB is not found", requestCardHolderDTO.getOib())
                ));

        if (Objects.equals(cardHolder.getStatus().getId(), CardHolderActivityStatus.INACTIVE.id)) {
            throw new CardHolderNotFoundException(
                    String.format(
                            "Card Holder with %s OIB is already %s",
                            requestCardHolderDTO.getOib(),
                            CardHolderActivityStatus.INACTIVE.name()
                    )
            );
        }

        Status newStatus = statusRepository.findById(CardHolderActivityStatus.INACTIVE.id)
                .orElseThrow(() -> new StatusDoesntExistException("Status doesnt exist"));

        cardHolder.setStatus(newStatus);

        fileGeneratorService.softDeletePreviousDocument(cardHolder);

        return cardHolderMapper.cardHolderToDeleteResponseDto(
                cardHolderRepository.save(
                        cardHolder
                ),
                newStatus
        );
    }
}
