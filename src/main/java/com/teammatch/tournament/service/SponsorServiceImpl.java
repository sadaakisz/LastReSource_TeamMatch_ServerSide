package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.ProfessionalTournament;
import com.teammatch.tournament.domain.model.Sponsor;
import com.teammatch.tournament.domain.repository.ProfessionalTournamentRepository;
import com.teammatch.tournament.domain.repository.SponsorRepository;
import com.teammatch.tournament.domain.repository.FreeTournamentRepository;
import com.teammatch.tournament.domain.service.SponsorService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService {


    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private ProfessionalTournamentRepository professionalTournamentRepository;

    @Override
    public Page<Sponsor> getAllSponsors(Pageable pageable) {
        return sponsorRepository.findAll(pageable);
    }

    @Override
    public Sponsor getSponsorById(Long sponsorId) {
        return sponsorRepository.findById(sponsorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sponsor", "Id", sponsorId));
    }

    @Override
    public Sponsor createSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    @Override
    public Sponsor updateSponsor(Long sponsorId, Sponsor sponsorRequest) {
        Sponsor sponsor = sponsorRepository.findById(sponsorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sponsor", "Id", sponsorId));
        return sponsorRepository.save(
                sponsor.setName(sponsorRequest.getName())
                        .setUrl(sponsorRequest.getUrl()));
    }

    @Override
    public ResponseEntity<?> deleteSponsor(Long sponsorId) {
        Sponsor sponsor = sponsorRepository.findById(sponsorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sponsor", "Id", sponsorId));
        sponsorRepository.delete(sponsor);
        return ResponseEntity.ok().build();
    }

    @Override
    public Sponsor assignSponsorProfessionalTournament(Long sponsorId, Long professionalTournamentId) {
        ProfessionalTournament professionalTournament = professionalTournamentRepository.findById(professionalTournamentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament", "Id", professionalTournamentId));
        return sponsorRepository.findById(sponsorId).map(sponsor -> {
            return sponsorRepository.save(sponsor.professionalTournamentWith(professionalTournament));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sponsor", "Id", sponsorId));

    }

    @Override
    public Sponsor unassignSponsorProfessionalTournament(Long sponsorId, Long professionalTournamentId) {
       ProfessionalTournament professionalTournament = professionalTournamentRepository.findById(professionalTournamentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament", "Id", professionalTournamentId));
        return sponsorRepository.findById(sponsorId).map(sponsor -> {
            return sponsorRepository.save(sponsor.unProfessionalTournamentWith(professionalTournament));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sponsor", "Id", sponsorId));
    }

    @Override
    public Page<Sponsor> getAllSponsorsByProfessionalTournamentId(Long professionalTournamentId, Pageable pageable) {
        return professionalTournamentRepository.findById(professionalTournamentId).map(professionalTournament -> {
            List<Sponsor> sponsors = professionalTournament.getSponsors();
            int sponsorsCount = sponsors.size();
            return new PageImpl<>(sponsors, pageable, sponsorsCount);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Tournament", "Id", professionalTournamentId));
    }

    @Override
    public Sponsor getSponsorByName(String name) {
        return sponsorRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Sponsor", "Name", name));
    }
}
