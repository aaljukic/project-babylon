package dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.card_holder;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dev.aaljukic.babylon.babylon_card_holder_service.web.validations.DigitOnly;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"firstName", "lastName", "oib", "statusId"})
public class RegisterRequestCardHolderDTO {
    @NotBlank(message = "firstName must be valid input")
    @Size(min = 1, max = 255, message = "firstName must not be empty")
    @JsonProperty("firstName")
    @Schema(example = "Pero")
    private String firstName;

    @NotBlank(message = "lastName must be valid input")
    @Size(min = 1, max = 255, message = "lastName must not be empty")
    @JsonProperty("lastName")
    @Schema(example = "Peric")
    private String lastName;

    @NotBlank(message = "OIB must be valid input")
    @Size(min = 11, max = 11, message = "OIB must be 11 characters long")
    @DigitOnly(message = "OIB must contain only digits")
    @JsonProperty("oib")
    @Schema(example = "01234567891")
    private String oib;


    @NotNull(message = "statusId must not be null")
    @Min(value = 1, message = "statusId - min is 1")
    @Max(value = 2, message = "statusId - max is 2")
    @JsonProperty("statusId")
    @Schema(example = "1")
    private Long statusId;
}

