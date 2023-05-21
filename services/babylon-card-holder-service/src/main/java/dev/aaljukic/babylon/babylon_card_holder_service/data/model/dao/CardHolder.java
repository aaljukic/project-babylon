package dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card_holder")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String oib;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
}
