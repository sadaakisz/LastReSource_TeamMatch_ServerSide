package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.ProfessionalTournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfessionalTournamentService {
    Page<ProfessionalTournament> getAllProfessionalTournaments(Pageable pageable);
    Page<ProfessionalTournament> getAllProfessionalTournamentsByOrganizerId(Long organizerId, Pageable pageable);
    ProfessionalTournament getProfessionalTournamentByIdAndOrganizerId(Long organizerId, Long professionalTournamentId);
    ProfessionalTournament getProfessionalTournamentById(Long professionalTournamentId);
    ProfessionalTournament createProfessionalTournament(Long organizerId, ProfessionalTournament professionalTournament);
    ProfessionalTournament updateProfessionalTournament(Long organizerId, Long professionalTournamentId, ProfessionalTournament professionalTournamentDetails);
    ResponseEntity<?> deleteProfessionalTournament(Long organizerId, Long professionalTournamentId);
    ProfessionalTournament assignProfessionalTournamentPlayer(Long professionalTournamentId, Long playerId);
    ProfessionalTournament unassignProfessionalTournamentPlayer(Long professionalTournamentId, Long playerId);
    Page<ProfessionalTournament> getAllProfessionalTournamentByPlayerId(Long playerId, Pageable pageable);
}
