package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FreeTournamentService {
    Page<FreeTournament> getAllFreeTournaments(Pageable pageable);
    Page<FreeTournament> getAllFreeTournamentsByOrganizerId(Long organizerId, Pageable pageable);
    FreeTournament getFreeTournamentByIdAndOrganizerId(Long organizerId, Long freeTournamentId);
    FreeTournament getFreeTournamentById(Long freeTournamentId);
    FreeTournament createFreeTournament(Long organizerId, FreeTournament freeTournament);
    FreeTournament updateFreeTournament(Long organizerId, Long tournamentId, FreeTournament freeTournamentDetails);
    ResponseEntity<?> deleteFreeTournament(Long organizerId, Long freeTournamentId);

}
