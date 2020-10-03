package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Organizer;
import com.teammatch.tournament.domain.repository.OrganizerRepository;
import com.teammatch.tournament.domain.service.OrganizerService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Override
    public Page<Organizer> getAllOrganizers(Pageable pageable) { return organizerRepository.findAll(pageable); }

    @Override
    public Organizer getOrganizerById(Long organizerId) {
        return organizerRepository.findById(organizerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Organizer", "Id", organizerId));
    }

    @Override
    public Organizer createOrganizer(Organizer organizer) { return organizerRepository.save(organizer); }

    @Override
    public Organizer updateOrganizer(Long organizerId, Organizer organizerRequest) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Organizer", "Id", organizerId));
        return organizerRepository.save(
                organizer.setUsername(organizerRequest.getUsername())
                    .setPassword(organizerRequest.getPassword()));
    }

    @Override
    public ResponseEntity<?> deleteOrganizer(Long organizerId) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Organizer", "Id", organizerId));
        organizerRepository.delete(organizer);
        return ResponseEntity.ok().build();
    }
}
