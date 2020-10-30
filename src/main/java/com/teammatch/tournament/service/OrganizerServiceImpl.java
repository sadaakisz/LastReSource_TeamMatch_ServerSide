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

import javax.persistence.MappedSuperclass;

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

        return organizerRepository.findById(organizerId).map(organizer1 -> {
            organizer1.setUsername(organizerRequest.getUsername());
            organizer1.setPassword(organizerRequest.getPassword());
            organizer1.setFirstName(organizerRequest.getFirstName());
            organizer1.setLastName(organizerRequest.getLastName());
            organizer1.setDescription(organizerRequest.getDescription());
            organizer1.setGender(organizerRequest.getGender());
            organizer1.setEmailAddress(organizerRequest.getEmailAddress());
            organizer1.setPhoneNumber(organizerRequest.getPhoneNumber());
            organizer1.setBirthDate(organizerRequest.getBirthDate());
            return organizerRepository.save(organizer1);
        }).orElseThrow(()->new ResourceNotFoundException("Organizer","Id",organizerId));
    }

    @Override
    public ResponseEntity<?> deleteOrganizer(Long organizerId) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Organizer", "Id", organizerId));
        organizerRepository.delete(organizer);
        return ResponseEntity.ok().build();
    }

    @Override
    public Organizer getOrganizerByUserName(String userName) {
        return organizerRepository.findByUsername(userName)
                .orElseThrow(()->new ResourceNotFoundException("Organizer","userName",userName));
    }
}
