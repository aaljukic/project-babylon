package dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.card_holder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.status.ResponseStatusDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"firstName", "lastName", "oib", "status"})
public class UpdateResponseCardHolderDTO {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("oib")
    private String oib;

    @JsonProperty("status")
    private ResponseStatusDTO statusDTO;
}
