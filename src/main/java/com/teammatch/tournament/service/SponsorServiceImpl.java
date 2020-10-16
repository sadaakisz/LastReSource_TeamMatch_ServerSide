package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Sponsor;
import com.teammatch.tournament.domain.model.Tournament;
import com.teammatch.tournament.domain.repository.SponsorRepository;
import com.teammatch.tournament.domain.repository.TournamentRepository;
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
    private TournamentRepository tournamentRepository;

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
    public Sponsor assignSponsorTournament(Long sponsorId, Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament", "Id", tournamentId));
        return sponsorRepository.findById(sponsorId).map(sponsor -> {
            return sponsorRepository.save(sponsor.tournamentWith(tournament));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sponsor", "Id", sponsorId));

    }

    @Override
    public Sponsor unassignSponsorTournament(Long sponsorId, Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament", "Id", tournamentId));
        return sponsorRepository.findById(sponsorId).map(sponsor -> {
            return sponsorRepository.save(sponsor.unTournamentWith(tournament));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sponsor", "Id", sponsorId));
    }

    @Override
    public Page<Sponsor> getAllSponsorsByTournamentId(Long tournamentId, Pageable pageable) {
        return tournamentRepository.findById(tournamentId).map( tournament -> {
            List<Sponsor> sponsors = tournament.getSponsors();
            int sponsorsCount = sponsors.size();
            return new PageImpl<>(sponsors, pageable, sponsorsCount);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Tournament", "Id", tournamentId));
    }

    @Override
    public Sponsor getSponsorByName(String name) {
        return sponsorRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Sponsor", "Name", name));
    }
}
