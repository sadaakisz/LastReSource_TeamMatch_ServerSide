package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SponsorService {

    Page<Sponsor> getAllSponsors(Pageable pageable);
    Sponsor getSponsorById(Long sponsorId);
    Sponsor createSponsor(Sponsor sponsor);
    Sponsor updateSponsor(Long sponsorId, Sponsor sponsorRequest);
    ResponseEntity<?> deleteSponsor(Long sponsorId);
    Sponsor assignSponsorProfessionalTournament(Long sponsorId, Long professionalTournamentId);
    Sponsor unassignSponsorProfessionalTournament(Long sponsorId, Long professionalTournamentId);
    Page<Sponsor> getAllSponsorsByProfessionalTournamentId(Long professionalTournamentId, Pageable pageable);
    Sponsor getSponsorByName(String name);
}