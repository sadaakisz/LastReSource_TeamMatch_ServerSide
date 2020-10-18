package com.teammatch.tournament.domain.repository;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.ProfessionalTournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionalTournamentRepository extends JpaRepository<ProfessionalTournament,Long> {
    Page<ProfessionalTournament> findByOrganizerId(Long organizerId, Pageable pageable);
    Optional<ProfessionalTournament> findByIdAndOrganizerId(Long id, Long organizerId);
}
