package com.teammatch.tournament.domain.repository.Tournament;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.TournamentMoreEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentMoreEnrollmentRepository extends JpaRepository<TournamentMoreEnrollment,Long> {
    Page<TournamentMoreEnrollment> findByOrganizerId(Long organizerId, Pageable pageable);
    Optional<TournamentMoreEnrollment> findByIdAndOrganizerId(Long id, Long organizerId);
}
