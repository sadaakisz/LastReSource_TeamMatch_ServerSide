package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Profile;
import com.teammatch.tournament.domain.repository.ProfileRepository;
import com.teammatch.tournament.domain.service.ProfileService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Profile> getAllProfiles(Pageable pageable) { return profileRepository.findAll(pageable); }

    @Override
    public Profile getProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Profile", "Id", profileId));
    }

    @Override
    public Profile createProfile(Profile profile) { return profileRepository.save(profile); }

    @Override
    public Profile updateProfile(Long profileId, Profile profileRequest) {
        Profile profile=profileRepository.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Profile", "Id", profileId));
        return profileRepository.save(
                profile.setFirstName(profileRequest.getFirstName())
                .setLastName(profileRequest.getLastName())
                .setUsername(profileRequest.getUsername())
                .setDescription(profileRequest.getDescription())
                .setGender(profileRequest.getGender())
                .setEmailAddress(profileRequest.getEmailAddress())
                .setPhoneNumber(profileRequest.getPhoneNumber())
                .setBirthDate(profileRequest.getBirthDate()));
    }

    @Override
    public ResponseEntity<?> deleteProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Profile", "Id", profileId));
        profileRepository.delete(profile);
        return ResponseEntity.ok().build();
    }
}
