package dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"isGenerated",})
public class CreateFileResponseDTO {
    @JsonProperty("isGenerated")
    private Boolean isGenerated;
}
