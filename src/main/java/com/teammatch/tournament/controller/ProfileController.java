package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Organizer;
import com.teammatch.tournament.domain.model.Profile;
import com.teammatch.tournament.domain.service.ProfileService;
import com.teammatch.tournament.resource.OrganizerResource;
import com.teammatch.tournament.resource.ProfileResource;
import com.teammatch.tournament.resource.SaveOrganizerResource;
import com.teammatch.tournament.resource.SaveProfileResource;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Profile", description = "Profile API")
@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/profiles")
    public Page<ProfileResource> getAllProfiles(Pageable pageable){
        List<ProfileResource> resourceList = profileService.getAllProfiles(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resourceList, pageable, resourceList.size());
    }

    @GetMapping("/profiles/{profileId}")
    public ProfileResource getProfileById(@PathVariable(name = "profileId") Long profileId){
        return convertToResource(profileService.getProfileById(profileId));
    }

    @PostMapping("/profiles")
    public ProfileResource createProfile(@Valid @RequestBody SaveProfileResource resource){
        return convertToResource(profileService.createProfile(convertToEntity(resource)));
    }

    @DeleteMapping("/profiles/{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable(name = "profileId") Long profileId){
        return profileService.deleteProfile(profileId);
    }

    @PutMapping("/profiles/{profileId}")
    public ProfileResource updateProfile(@PathVariable(name = "profileId") Long profileId,
                                         @Valid @RequestBody SaveProfileResource resource){
        return convertToResource(profileService.updateProfile(profileId, convertToEntity(resource)));
    }

    private Profile convertToEntity(SaveProfileResource resource) { return mapper.map(resource, Profile.class); }

    private ProfileResource convertToResource(Profile entity) { return mapper.map(entity, ProfileResource.class); }
}
