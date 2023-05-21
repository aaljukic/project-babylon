package dev.aaljukic.babylon.babylon_card_holder_service.services;

import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.CardHolder;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.file.CreateFileRequestDTO;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.file.CreateFileResponseDTO;

public interface FileGeneratorService {

    CreateFileResponseDTO generateFileForCardHolder(CreateFileRequestDTO createFileRequestDTO);

    void softDeletePreviousDocument(CardHolder cardHolder);
}
