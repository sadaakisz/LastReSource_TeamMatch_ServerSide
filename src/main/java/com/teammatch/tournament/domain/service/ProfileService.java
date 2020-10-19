package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    Page<Profile> getAllProfiles(Pageable pageable);
    Profile getProfileById(Long profileId);
    Profile createProfile(Profile profile);
    Profile updateProfile(Long profileId, Profile profileRequest);
    ResponseEntity<?> deleteProfile(Long profileId);
}
