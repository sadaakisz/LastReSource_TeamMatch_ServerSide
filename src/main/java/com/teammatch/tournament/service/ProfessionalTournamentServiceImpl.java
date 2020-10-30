package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.model.ProfessionalTournament;
import com.teammatch.tournament.domain.repository.FreeTournamentRepository;
import com.teammatch.tournament.domain.repository.OrganizerRepository;
import com.teammatch.tournament.domain.repository.PlayerRepository;
import com.teammatch.tournament.domain.repository.ProfessionalTournamentRepository;
import com.teammatch.tournament.domain.service.ProfessionalTournamentService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalTournamentServiceImpl implements ProfessionalTournamentService {
    @Autowired
    private ProfessionalTournamentRepository professionalTournamentRepository;

    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public Page<ProfessionalTournament> getAllProfessionalTournaments(Pageable pageable) {
        return professionalTournamentRepository.findAll(pageable);
    }

    @Override
    public Page<ProfessionalTournament> getAllProfessionalTournamentsByOrganizerId(Long organizerId, Pageable pageable) {
        return professionalTournamentRepository.findByOrganizerId(organizerId, pageable);
    }

    @Override
    public ProfessionalTournament getProfessionalTournamentByIdAndOrganizerId(Long organizerId, Long professionalTournamentId) {
        return professionalTournamentRepository.findByIdAndOrganizerId(professionalTournamentId , organizerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Professional Tournament not found with Id " + professionalTournamentId +
                                " and OrganizerId " + organizerId));
    }

    @Override
    public ProfessionalTournament getProfessionalTournamentById(Long professionalTournamentId) {
        return professionalTournamentRepository.findById(professionalTournamentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament", "Id", professionalTournamentId));
    }

    @Override
    public ProfessionalTournament createProfessionalTournament(Long organizerId, ProfessionalTournament professionalTournament) {
        return organizerRepository.findById(organizerId).map(organizer->{
            professionalTournament.setOrganizer(organizer);
            return professionalTournamentRepository.save(professionalTournament);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Organizer", "Id", organizerId));
    }

    @Override
    public ProfessionalTournament updateProfessionalTournament(Long organizerId, Long professionalTournamentId, ProfessionalTournament professionalTournamentDetails) {
        if(!organizerRepository.existsById(organizerId))
            throw new ResourceNotFoundException("Organizer", "Id", organizerId);
        return professionalTournamentRepository.findById(professionalTournamentId).map(professionalTournament -> {
            professionalTournament.setName(professionalTournamentDetails.getName());
            professionalTournament.setDescription(professionalTournamentDetails.getDescription());
            professionalTournament.setPrize(professionalTournamentDetails.getPrize());
            professionalTournament.setPublicTournament(professionalTournamentDetails.getPublicTournament());
            professionalTournament.setCode(professionalTournamentDetails.getCode());
            professionalTournament.setMaxTeams(professionalTournamentDetails.getMaxTeams());

            return professionalTournamentRepository.save(professionalTournament);
        }).orElseThrow(() -> new ResourceNotFoundException("Tournament", "Id", professionalTournamentId));
    }

    @Override
    public ResponseEntity<?> deleteProfessionalTournament(Long organizerId, Long professionalTournamentId) {
        return professionalTournamentRepository.findByIdAndOrganizerId(professionalTournamentId, organizerId).map(professionalTournament -> {
            professionalTournamentRepository.delete(professionalTournament);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Tournament not found with Id " + professionalTournamentId + " and OrganizerId " + organizerId));
    }

    @Override
    public ProfessionalTournament assignProfessionalTournamentPlayer(Long professionalTournamentId, Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("Player", "Id", playerId));
        return professionalTournamentRepository.findById(professionalTournamentId).map(professionalTournament -> {
            return professionalTournamentRepository.save(professionalTournament.addToPlayer(player));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "ProfessionalTournament", "Id", professionalTournamentId));
    }

    @Override
    public ProfessionalTournament unassignProfessionalTournamentPlayer(Long professionalTournamentId, Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("Player", "Id", playerId));
        return professionalTournamentRepository.findById(professionalTournamentId).map(freeTournament -> {
            return professionalTournamentRepository.save(freeTournament.deleteFromPlayer(player));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "ProfessionalTournament", "Id", professionalTournamentId));
    }

    @Override
    public Page<ProfessionalTournament> getAllProfessionalTournamentByPlayerId(Long playerId, Pageable pageable) {
        return playerRepository.findById(playerId).map(player -> {
            List<ProfessionalTournament> professionalTournaments = player.getProfessionalTournaments();
            return new PageImpl<>(professionalTournaments, pageable, professionalTournaments.size());
        }).orElseThrow(() -> new ResourceNotFoundException("Player", "Id", playerId));
    }
}
