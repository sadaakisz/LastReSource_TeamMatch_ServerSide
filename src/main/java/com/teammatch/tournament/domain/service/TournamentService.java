package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TournamentService {
    Page<Tournament> getAllTournaments(Pageable pageable);
    Page<Tournament> getAllTournamentsByOrganizerId(Long organizerId, Pageable pageable);
    Tournament getTournamentByIdAndOrganizerId(Long organizerId, Long tournamentId);
    Tournament getTournamentById(Long tournamentId);
    Tournament createTournament(Long organizerId, Tournament tournament);
    Tournament updateTournament(Long organizerId, Long tournamentId, Tournament tournamentDetails);
    ResponseEntity<?> deleteTournament(Long organizerId, Long tournamentId);

}
