package dev.aaljukic.babylon.babylon_card_holder_service.services.impl;

import dev.aaljukic.babylon.babylon_card_holder_service.common.enums.CardHolderActivityStatus;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.CardHolder;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.file.CreateFileRequestDTO;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.file.CreateFileResponseDTO;
import dev.aaljukic.babylon.babylon_card_holder_service.data.repository.CardHolderRepository;
import dev.aaljukic.babylon.babylon_card_holder_service.data.repository.StatusRepository;
import dev.aaljukic.babylon.babylon_card_holder_service.services.FileGeneratorService;
import dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions.CardHolderNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FileGeneratorServiceImpl implements FileGeneratorService {
    private static final String FILE_DIRECTORY = System.getProperty("user.dir") + File.separator + "generated_cards";

    private final CardHolderRepository cardHolderRepository;

    private final StatusRepository statusRepository;

    @Override
    public CreateFileResponseDTO generateFileForCardHolder(CreateFileRequestDTO createFileRequestDTO) {
        CardHolder cardHolder = cardHolderRepository.findByOib(createFileRequestDTO.getOib())
                .orElseThrow(() -> new CardHolderNotFoundException(
                        String.format("Card Holder with %s OIB is not found", createFileRequestDTO.getOib())
                ));

        StringBuilder textFileContent = new StringBuilder()
                .append(cardHolder.getFirstName())
                .append(",")
                .append(cardHolder.getLastName())
                .append(",")
                .append(cardHolder.getOib())
                .append(",")
                .append(cardHolder.getStatus().getDescription())
                .append("\n");

        File directory = new File(FILE_DIRECTORY);
        if (! directory.exists()) {
            directory.mkdirs();
        }

        softDeletePreviousDocument(cardHolder);

        // create new file with active
        File file = new File(
                FILE_DIRECTORY +
                        File.separator +
                        cardHolder.getOib() +
                        "-" +
                        cardHolder.getStatus().getDescription() +
                        "_" +
                        LocalDateTime.now() +
                        ".txt"
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textFileContent.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return CreateFileResponseDTO.builder().isGenerated(true).build();
    }

    @Override
    public void softDeletePreviousDocument(CardHolder cardHolder) {
        String activeCardHolderStatusAndOibPrefix = cardHolder.getOib() + '-' + CardHolderActivityStatus.ACTIVE.name();
        String inactiveCardHolderStatusAndOibPrefix = cardHolder.getOib() + '-' + CardHolderActivityStatus.INACTIVE.name();

        // search for active cardholder file and rename it to inactive, update content
        try {
            Files.walk(Paths.get(FILE_DIRECTORY))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        String fileName = path.getFileName().toString();
                        if (fileName.startsWith(activeCardHolderStatusAndOibPrefix)) {
                            String newFileName = fileName.replace(activeCardHolderStatusAndOibPrefix, inactiveCardHolderStatusAndOibPrefix);
                            try {
                                Path newPath = Files.move(path, path.resolveSibling(newFileName));

                                String content = new String(Files.readAllBytes(newPath));

                                content = content.replace(
                                        CardHolderActivityStatus.ACTIVE.name(),
                                        CardHolderActivityStatus.INACTIVE.name()
                                );

                                Files.write(newPath, content.getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
