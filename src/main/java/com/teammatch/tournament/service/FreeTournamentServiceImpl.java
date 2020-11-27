package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.repository.OrganizerRepository;
import com.teammatch.tournament.domain.repository.Tournament.FreeTournamentRepository;
import com.teammatch.tournament.domain.repository.PlayerRepository;
import com.teammatch.tournament.domain.service.FreeTournamentService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FreeTournamentServiceImpl implements FreeTournamentService {
    @Autowired
    private FreeTournamentRepository freeTournamentRepository;

    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public Page<FreeTournament> getAllFreeTournaments(Pageable pageable) {
        return freeTournamentRepository.findAll(pageable);
    }


    @Override
    public Page<FreeTournament> getAllFreeTournamentsByOrganizerId(Long organizerId, Pageable pageable) {
        return freeTournamentRepository.findByOrganizerId(organizerId, pageable);
    }

    @Override
    public FreeTournament getFreeTournamentByIdAndOrganizerId(Long organizerId, Long freeTournamentId) {
        return freeTournamentRepository.findByIdAndOrganizerId(freeTournamentId , organizerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament not found with Id " + freeTournamentId +
                                " and OrganizerId " + organizerId));
    }

    @Override
    public FreeTournament getFreeTournamentById(Long freeTournamentId) {
        return freeTournamentRepository.findById(freeTournamentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament", "Id", freeTournamentId));
    }

    @Override
    public FreeTournament createFreeTournament(Long organizerId, FreeTournament freeTournament) {
        return organizerRepository.findById(organizerId).map(organizer->{
            freeTournament.setOrganizer(organizer);
            return freeTournamentRepository.save(freeTournament);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Organizer", "Id", organizerId));
    }

    @Override
    public FreeTournament updateFreeTournament(Long organizerId, Long freeTournamentId, FreeTournament freeTournamentDetails) {
        if(!organizerRepository.existsById(organizerId))
            throw new ResourceNotFoundException("Organizer", "Id", organizerId);
        return freeTournamentRepository.findById(freeTournamentId).map(freeTournament -> {
            freeTournament.setName(freeTournamentDetails.getName());
            freeTournament.setDescription(freeTournamentDetails.getDescription());
            freeTournament.setPrize(freeTournamentDetails.getPrize());
            freeTournament.setPublicTournament(freeTournamentDetails.getPublicTournament());
            freeTournament.setCode(freeTournamentDetails.getCode());
            freeTournament.setMaxTeams(freeTournamentDetails.getMaxTeams());

            return freeTournamentRepository.save(freeTournament);
        }).orElseThrow(() -> new ResourceNotFoundException("Tournament", "Id", freeTournamentId));
    }

    @Override
    public ResponseEntity<?> deleteFreeTournament(Long organizerId, Long freeTournamentId) {
        return freeTournamentRepository.findByIdAndOrganizerId(freeTournamentId, organizerId).map(freeTournament -> {
            freeTournamentRepository.delete(freeTournament);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Tournament not found with Id " + freeTournamentId + " and OrganizerId " + organizerId));
    }

    @Override
    public FreeTournament assignFreeTournamentPlayer(Long freeTournamentId, Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("Player", "Id", playerId));
        return freeTournamentRepository.findById(freeTournamentId).map(freeTournament -> {
            return freeTournamentRepository.save(freeTournament.addToPlayer(player));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "FreeTournament", "Id", freeTournamentId));
    }

    @Override
    public FreeTournament unassignFreeTournamentPlayer(Long freeTournamentId, Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("Player", "Id", playerId));
        return freeTournamentRepository.findById(freeTournamentId).map(freeTournament -> {
            return freeTournamentRepository.save(freeTournament.deleteFromPlayer(player));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "FreeTournament", "Id", freeTournamentId));
    }
        @Override
        public Page<FreeTournament> getAllFreeTournamentsByPlayerId (Long playerId, Pageable pageable){
            return playerRepository.findById(playerId).map(player -> {
                List<FreeTournament> freeTournaments = player.getFreeTournaments();
                return new PageImpl<>(freeTournaments, pageable, freeTournaments.size());
            }).orElseThrow(() -> new ResourceNotFoundException("Player", "Id", playerId));

    }
}

