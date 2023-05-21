package dev.aaljukic.babylon.babylon_card_holder_service.web.controller;

import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.*;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.card_holder.*;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.file.CreateFileRequestDTO;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.file.CreateFileResponseDTO;
import dev.aaljukic.babylon.babylon_card_holder_service.services.CardHolderService;
import dev.aaljukic.babylon.babylon_card_holder_service.services.FileGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/card-holders")
@RequiredArgsConstructor
public class CardHolderController {

    private final CardHolderService cardHolderService;

    private final FileGeneratorService fileGeneratorService;

    @PostMapping("search")
    @Operation(
            description = "Find Card Holder By OIB",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found Card Holder by OIB",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                        @ExampleObject(value = "{\"data\": {\"firstName\": \"Pero\", \"lastName\": \"Peric\", \"oib\": \"12345678901\", \"status\": {\"id\": 1, \"description\":\"ACTIVE\"} }, \"status\": \"OK\", \"message\": \"Successfully found Card Holder\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(value = "{\"data\": {\"errors\": [\"OIB must be 11 characters long\"] }, \"status\": \"BAD_REQUEST\", \"message\": \"Bad Request - Validation failed\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }

                            )
                    ),
            }
    )
    @ResponseBody
    public ResponseEntity<ApiCustomResponse<Object>> findCardHolderByOIB(
            @RequestBody @Valid FindRequestCardHolderDTO requestCardHolderDTO
    ) {
        log.info("findCardHolderByOIB called");

        FindResponseCardHolderDTO cardHolder = cardHolderService.readCardHolderAccount(requestCardHolderDTO);

        ApiCustomResponse<Object> response = ApiCustomResponse.builder()
                .data(cardHolder)
                .status(HttpStatus.OK.name())
                .message("Successfully found Card Holder")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(
            description = "Register New Card Holder",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully registered new Card Holder",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(value = "{\"data\": {\"firstName\": \"Pero\", \"lastName\": \"Peric\", \"oib\": \"12345678901\", \"status\": {\"id\": 1, \"description\":\"ACTIVE\"} }, \"status\": \"OK\", \"message\": \"Successfully registered new Card Holder\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(value = "{\"data\": {\"errors\": [\"OIB must be valid input\"] }, \"status\": \"BAD_REQUEST\", \"message\": \"Bad Request - Validation failed\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }

                            )
                    ),
            }
    )
    @ResponseBody
    public ResponseEntity<ApiCustomResponse<Object>> registerCardHolder(
            @RequestBody @Valid RegisterRequestCardHolderDTO requestCardHolderDTO
    ) {
        log.info("registerCardHolder called");

        RegisterResponseCardHolderDTO cardHolder = cardHolderService.createCardHolderAccount(requestCardHolderDTO);

        ApiCustomResponse<Object> response = ApiCustomResponse.builder()
                .data(cardHolder)
                .status(HttpStatus.OK.name())
                .message("Successfully registered new Card Holder")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping
    @Operation(
            description = "Update Card Holder's any of fields by OIB",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated Card Holder",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(value = "{\"data\": {\"firstName\": \"Pero Pervan\", \"lastName\": \"Peric\", \"oib\": \"12345678901\", \"status\": {\"id\": 1, \"description\":\"ACTIVE\"} }, \"status\": \"OK\", \"message\": \"Successfully updated Card Holder\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(value = "{\"data\": {\"errors\": [\"if provided, firstName must not be empty\"] }, \"status\": \"BAD_REQUEST\", \"message\": \"Bad Request - Validation failed\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }

                            )
                    ),
            }
    )
    @ResponseBody
    public ResponseEntity<ApiCustomResponse<Object>> updateCardHolder(
            @RequestBody @Valid UpdateRequestCardHolderDTO requestCardHolderDTO
    ) {
        log.info("updateCardHolder called");

        UpdateResponseCardHolderDTO cardHolder = cardHolderService.updateCardHolderAccount(requestCardHolderDTO);

        ApiCustomResponse<Object> response = ApiCustomResponse.builder()
                .data(cardHolder)
                .status(HttpStatus.OK.name())
                .message("Successfully updated Card Holder")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Operation(
            description = "Delete Card Holder",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted Card Holder",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(value = "{\"data\": {\"firstName\": \"Pero\", \"lastName\": \"Peric\", \"oib\": \"12345678901\", \"status\": {\"id\": 2, \"description\":\"INACTIVE\"} }, \"status\": \"OK\", \"message\": \"Successfully deleted Card Holder\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(value = "{\"data\": {\"errors\": [\"OIB must be valid input\"] }, \"status\": \"BAD_REQUEST\", \"message\": \"Bad Request - Validation failed\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }

                            )
                    ),
            }
    )
    @ResponseBody
    public ResponseEntity<ApiCustomResponse<Object>> deleteCardHolder(
            @RequestBody @Valid DeleteRequestCardHolderDTO requestCardHolderDTO
    ) {
        log.info("deleteCardHolder called");

        DeleteResponseCardHolderDTO cardHolder = cardHolderService.deleteCardHolderAccount(requestCardHolderDTO);

        ApiCustomResponse<Object> response = ApiCustomResponse.builder()
                .data(cardHolder)
                .status(HttpStatus.OK.name())
                .message("Successfully deleted Card Holder")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("generate-card")
    @Operation(
            description = "Generate Card for Card Holder",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully generated Card for Card Holder",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(value = "{\"data\": {\"isGenerated\": \"true\"}, \"status\": \"OK\", \"message\": \"Your request to generate card is successfully received and ready to be reviewed\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(value = "{\"data\": {\"errors\": [\"OIB must be 11 characters long\"] }, \"status\": \"BAD_REQUEST\", \"message\": \"Bad Request - Validation failed\", \"timestamp\": \"1010-10-10T10:10:10.101010\"}")
                                    }

                            )
                    ),
            }
    )
    @ResponseBody
    public ResponseEntity<ApiCustomResponse<Object>> generateCardForCardHolder(
            @RequestBody @Valid CreateFileRequestDTO createFileRequestDTO
    ) {
        log.info("generateCardForCardHolder called");

        CreateFileResponseDTO generated = fileGeneratorService.generateFileForCardHolder(createFileRequestDTO);

        ApiCustomResponse<Object> response = ApiCustomResponse.builder()
                .data(generated)
                .status(HttpStatus.OK.name())
                .message("Your request to generate card is successfully received and ready to be reviewed")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
