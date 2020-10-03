package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Organizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OrganizerService {
    Page<Organizer> getAllOrganizers(Pageable pageable);
    Organizer getOrganizerById(Long organizerId);
    Organizer createOrganizer(Organizer organizer);
    Organizer updateOrganizer(Long organizerId, Organizer organizer);
    ResponseEntity<?> deleteOrganizer(Long organizerId);
}
