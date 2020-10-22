package com.teammatch.tournament.domain.repository;

import com.teammatch.tournament.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
