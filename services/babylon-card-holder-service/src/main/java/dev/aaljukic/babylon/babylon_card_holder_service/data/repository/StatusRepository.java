package dev.aaljukic.babylon.babylon_card_holder_service.data.repository;

import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
