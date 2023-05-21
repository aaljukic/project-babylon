package dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.card_holder;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dev.aaljukic.babylon.babylon_card_holder_service.web.validations.DigitOnly;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"firstName", "lastName", "oib"})
public class UpdateRequestCardHolderDTO {
    @Size(min = 1, max = 255, message = "if provided, firstName must not be empty")
    @JsonProperty("firstName")
    @Schema(example = "Pero Pervan")
    private String firstName;

    @Size(min = 1, max = 255, message = "if provided, lastName must not be empty")
    @JsonProperty("lastName")
    @Schema(example = "Peric")
    private String lastName;

    @NotBlank(message = "OIB must be valid input")
    @Size(min = 11, max = 11, message = "OIB must be 11 characters long")
    @DigitOnly(message = "OIB must contain only digits")
    @JsonProperty("oib")
    @Schema(example = "01234567891")
    private String oib;
}

