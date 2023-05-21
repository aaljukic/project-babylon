package dev.aaljukic.babylon.babylon_card_holder_service.data.repository;

import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {
    Optional<CardHolder> findByOib(String oib);
}
