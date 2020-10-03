package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Tournament;
import com.teammatch.tournament.domain.repository.TournamentRepository;
import com.teammatch.tournament.domain.service.TournamentService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class TournamentServiceImpl implements TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public Page<Tournament> getAllTournaments(Pageable pageable) {
        return tournamentRepository.findAll(pageable);
    }


    @Override
    public Page<Tournament> getAllTournamentsByOrganizerId(Long organizerId, Pageable pageable) {
        return tournamentRepository.findByOrganizerId(organizerId, pageable);
    }

    @Override
    public Tournament getTournamentById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament", "Id", tournamentId));
    }

    @Override
    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament updateTournament(Long tournamentId, Tournament tournamentDetails) {
        return tournamentRepository.findById(tournamentId).map(tournament -> {
            tournament.setName(tournamentDetails.getName());
            return tournamentRepository.save(tournament);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Tournament", "Id", tournamentId));
    }

    @Override
    public ResponseEntity<?> deleteTournament(Long tournamentId) {
        return tournamentRepository.findById(tournamentId).map(tournament -> {
            tournamentRepository.delete(tournament);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Tournament", "Id", tournamentId));
    }
}
