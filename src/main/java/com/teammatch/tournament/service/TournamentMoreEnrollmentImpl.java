package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.model.TournamentMoreEnrollment;
import com.teammatch.tournament.domain.repository.OrganizerRepository;
import com.teammatch.tournament.domain.repository.PlayerRepository;
import com.teammatch.tournament.domain.repository.Tournament.TournamentMoreEnrollmentRepository;
import com.teammatch.tournament.domain.service.TournamentMoreEnrollmentService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentMoreEnrollmentImpl implements TournamentMoreEnrollmentService {


    @Autowired
    private TournamentMoreEnrollmentRepository tournamentMoreEnrollmentRepository;

    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public Page<TournamentMoreEnrollment> getAllTournamentMoreEnrollments(Pageable pageable) {
        return tournamentMoreEnrollmentRepository.findAll(pageable);
    }

    @Override
    public Page<TournamentMoreEnrollment> getAllTournamentMoreEnrollmentsByOrganizerId(Long organizerId, Pageable pageable) {
        return tournamentMoreEnrollmentRepository.findByOrganizerId(organizerId,pageable);
    }

    @Override
    public TournamentMoreEnrollment getTournamentMoreEnrollmentByIdAndOrganizerId(Long organizerId, Long tournamentMoreEnrollmentId) {
        return tournamentMoreEnrollmentRepository.findByIdAndOrganizerId(tournamentMoreEnrollmentId , organizerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament not found with Id " + tournamentMoreEnrollmentId +
                                " and OrganizerId " + organizerId));
    }

    @Override
    public TournamentMoreEnrollment getTournamentMoreEnrollmentById(Long tournamentMoreEnrollmentId) {
        return tournamentMoreEnrollmentRepository.findById(tournamentMoreEnrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tournament", "Id", tournamentMoreEnrollmentId));
    }

    @Override
    public TournamentMoreEnrollment createTournamentMoreEnrollment(Long organizerId, TournamentMoreEnrollment tournamentMoreEnrollment) {
        return organizerRepository.findById(organizerId).map(organizer->{
            tournamentMoreEnrollment.setOrganizer(organizer);
            return tournamentMoreEnrollmentRepository.save(tournamentMoreEnrollment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Organizer", "Id", organizerId));
    }

    @Override
    public TournamentMoreEnrollment updateTournamentMoreEnrollment(Long organizerId, Long tournamentMoreEnrollmentId, TournamentMoreEnrollment tournamentMoreEnrollmentDetails) {
        if(!organizerRepository.existsById(organizerId))
            throw new ResourceNotFoundException("Organizer", "Id", organizerId);
        return tournamentMoreEnrollmentRepository.findById(tournamentMoreEnrollmentId).map(tournamentMoreEnrollment -> {
            tournamentMoreEnrollment.setName(tournamentMoreEnrollmentDetails.getName());
            tournamentMoreEnrollment.setDescription(tournamentMoreEnrollmentDetails.getDescription());
            tournamentMoreEnrollment.setPrize(tournamentMoreEnrollmentDetails.getPrize());
            tournamentMoreEnrollment.setPublicTournament(tournamentMoreEnrollmentDetails.getPublicTournament());
            tournamentMoreEnrollment.setCode(tournamentMoreEnrollmentDetails.getCode());
            tournamentMoreEnrollment.setMaxTeams(tournamentMoreEnrollmentDetails.getMaxTeams());

            tournamentMoreEnrollment.setEnrollmentPlayer(tournamentMoreEnrollmentDetails.getEnrollmentPlayer());
            tournamentMoreEnrollment.setCommissionOrganizer(tournamentMoreEnrollmentDetails.getCommissionOrganizer());
            tournamentMoreEnrollment.setPotChampion(tournamentMoreEnrollmentDetails.getPotChampion());
            return tournamentMoreEnrollmentRepository.save(tournamentMoreEnrollment);
        }).orElseThrow(() -> new ResourceNotFoundException("Tournament", "Id", tournamentMoreEnrollmentId));
    }

    @Override
    public ResponseEntity<?> deleteTournamentMoreEnrollment(Long organizerId, Long tournamentMoreEnrollmentId) {
        return tournamentMoreEnrollmentRepository.findByIdAndOrganizerId(tournamentMoreEnrollmentId, organizerId).map(tournamentMoreEnrollment -> {
            tournamentMoreEnrollmentRepository.delete(tournamentMoreEnrollment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Tournament not found with Id " + tournamentMoreEnrollmentId + " and OrganizerId " + organizerId));
    }

    @Override
    public TournamentMoreEnrollment assignTournamentMoreEnrollmentPlayer(Long tournamentMoreEnrollmentId, Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("Player", "Id", playerId));
        return tournamentMoreEnrollmentRepository.findById(tournamentMoreEnrollmentId).map(tournamentMoreEnrollment -> {
            return tournamentMoreEnrollmentRepository.save(tournamentMoreEnrollment.addToPlayer(player));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "TournamentMoreEnrollment", "Id", tournamentMoreEnrollmentId));
    }

    @Override
    public TournamentMoreEnrollment unassignTournamentMoreEnrollmentPlayer(Long tournamentMoreEnrollmentId, Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("Player", "Id", playerId));
        return tournamentMoreEnrollmentRepository.findById(tournamentMoreEnrollmentId).map(tournamentMoreEnrollment -> {
            return tournamentMoreEnrollmentRepository.save(tournamentMoreEnrollment.deleteFromPlayer(player));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "TournamentMoreEnrollment", "Id", tournamentMoreEnrollmentId));
    }

    @Override
    public Page<TournamentMoreEnrollment> getAllTournamentMoreEnrollmentsByPlayerId(Long playerId, Pageable pageable) {
        return playerRepository.findById(playerId).map(player -> {
            List<TournamentMoreEnrollment> tournamentMoreEnrollments = player.getTournamentMoreEnrollments();
            return new PageImpl<>(tournamentMoreEnrollments, pageable, tournamentMoreEnrollments.size());
        }).orElseThrow(() -> new ResourceNotFoundException("Player", "Id", playerId));
    }
}
