package dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.card_holder;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dev.aaljukic.babylon.babylon_card_holder_service.web.validations.DigitOnly;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"oib"})
public class DeleteRequestCardHolderDTO {
    @NotBlank(message = "OIB must be valid input")
    @Size(min = 11, max = 11, message = "OIB must be 11 characters long")
    @DigitOnly(message = "OIB must contain only digits")
    @JsonProperty("oib")
    @Schema(example = "01234567891")
    private String oib;
}

