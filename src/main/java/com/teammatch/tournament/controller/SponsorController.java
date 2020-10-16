package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Sponsor;
import com.teammatch.tournament.domain.service.SponsorService;
import com.teammatch.tournament.resource.SaveSponsorResource;
import com.teammatch.tournament.resource.SponsorResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SponsorController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SponsorService sponsorService;

    @GetMapping("/sponsors")
    public Page<SponsorResource> getAllSponsors(Pageable pageable) {

        Page<Sponsor> sponsorsPage = sponsorService.getAllSponsors(pageable);
        List<SponsorResource> resources = sponsorsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/sponsors/{sponsorId}")
    public SponsorResource getSponsorById(@PathVariable(value = "sponsorId") Long sponsorId) {
        return convertToResource(sponsorService.getSponsorById(sponsorId));
    }

    @PostMapping("/sponsors")
    public SponsorResource createSponsor(
            @Valid @RequestBody SaveSponsorResource resource) {
        Sponsor sponsor = convertToEntity(resource);
        return convertToResource(sponsorService.createSponsor(sponsor));

    }

    @PutMapping("/sponsors/{sponsorId}")
    public SponsorResource updateSponsor(@PathVariable Long sponsorId,
                                   @Valid @RequestBody SaveSponsorResource resource) {
        Sponsor sponsor = convertToEntity(resource);
        return convertToResource(
                sponsorService.updateSponsor(sponsorId, sponsor));
    }

    @DeleteMapping("/sponsors/{sponsorId}")
    public ResponseEntity<?> deleteSponsor(@PathVariable Long sponsorId) {
        return sponsorService.deleteSponsor(sponsorId);
    }

    @GetMapping("/tournaments/{tournamentId}/sponsors")
    public Page<SponsorResource> getAllSponsorsByTagId(
            @PathVariable(name = "tournamentId") Long tournamentId,
            Pageable pageable) {
        Page<Sponsor> sponsorsPage = sponsorService.getAllSponsorsByTournamentId(tournamentId, pageable);
        List<SponsorResource> resources = sponsorsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());

    }

    @PostMapping("/sponsors/{sponsorId}/tournaments/{tournamentId}")
    public SponsorResource assignSponsorTournament(
            @PathVariable(name = "sponsorId") Long sponsorId,
            @PathVariable(name = "tournamentId") Long tournamentId) {
        return convertToResource(sponsorService.assignSponsorTournament(sponsorId, tournamentId));
    }

    @DeleteMapping("/sponsors/{sponsorId}/tournaments/{tournamentId}")
    public SponsorResource unassignSponsorTournament(
            @PathVariable(name = "sponsorId") Long sponsorId,
            @PathVariable(name = "tournamentId") Long tournamentId) {
        return convertToResource(sponsorService.unassignSponsorTournament(sponsorId, tournamentId));
    }

    private Sponsor convertToEntity(SaveSponsorResource resource) {
        return mapper.map(resource, Sponsor.class);
    }

    private SponsorResource convertToResource(Sponsor entity) {
        return mapper.map(entity, SponsorResource.class);
    }

}
