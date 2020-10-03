package com.teammatch.tournament.domain.repository;
import com.teammatch.tournament.domain.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface TournamentRepository {
    Page<Tournament> findByOrganizerId(Long organizerId, Pageable pageable);
    Optional<Tournament> findByIdAndOrganizerId(Long id, Long organizerId);
}
