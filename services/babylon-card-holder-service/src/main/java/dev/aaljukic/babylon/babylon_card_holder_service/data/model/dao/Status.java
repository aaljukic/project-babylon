package dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ref_status")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String description;

    @OneToMany(mappedBy = "status")
    private List<CardHolder> cardHolders;
}
