package dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.status;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatusDTO {
    private Long id;
    private String description;
}
