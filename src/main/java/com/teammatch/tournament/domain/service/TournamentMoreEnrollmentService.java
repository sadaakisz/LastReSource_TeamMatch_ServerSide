package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.TournamentMoreEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TournamentMoreEnrollmentService {
    Page<TournamentMoreEnrollment> getAllTournamentMoreEnrollments(Pageable pageable);
    Page<TournamentMoreEnrollment> getAllTournamentMoreEnrollmentsByOrganizerId(Long organizerId, Pageable pageable);
    TournamentMoreEnrollment getTournamentMoreEnrollmentByIdAndOrganizerId(Long organizerId, Long tournamentMoreEnrollmentId);
    TournamentMoreEnrollment getTournamentMoreEnrollmentById(Long tournamentMoreEnrollmentId);
    TournamentMoreEnrollment createTournamentMoreEnrollment(Long organizerId, TournamentMoreEnrollment tournamentMoreEnrollmentId);
    TournamentMoreEnrollment updateTournamentMoreEnrollment(Long organizerId, Long tournamentMoreEnrollmentId, TournamentMoreEnrollment tournamentMoreEnrollmentDetails);
    ResponseEntity<?> deleteTournamentMoreEnrollment(Long organizerId, Long tournamentMoreEnrollmentId);
    TournamentMoreEnrollment assignTournamentMoreEnrollmentPlayer(Long tournamentMoreEnrollmentId, Long playerId);
    TournamentMoreEnrollment unassignTournamentMoreEnrollmentPlayer(Long tournamentMoreEnrollmentId, Long playerId);
    Page<TournamentMoreEnrollment> getAllTournamentMoreEnrollmentsByPlayerId(Long playerId, Pageable pageable);
}
