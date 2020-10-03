package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TournamentService {
    Page<Tournament> getAllTournaments(Pageable pageable);
    Page<Tournament> getAllTournamentsByPostId(Long postId, Pageable pageable);
    Tournament getTournamentById(Long tournamentId);
    Tournament createTournament(Tournament tournament);
    Tournament updateTournament(Long tagId, Tournament tournamentDetails);
    ResponseEntity<?> deleteTournament(Long tournamentId);

}
