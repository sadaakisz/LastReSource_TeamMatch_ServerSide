package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Organizer;
import com.teammatch.tournament.domain.service.OrganizerService;
import com.teammatch.tournament.resource.OrganizerResource;
import com.teammatch.tournament.resource.SaveOrganizerResource;
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
public class OrganizerController {
    @Autowired
    OrganizerService organizerService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/organizers")
    public Page<OrganizerResource> getAllOrganizers(Pageable pageable){
        List<OrganizerResource> resourceList = organizerService.getAllOrganizers(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resourceList, pageable,resourceList.size());
    }
    @GetMapping("/organizers/{organizerId}")
    public OrganizerResource getOrganizerById(@PathVariable(name = "organizerId") Long organizerId){
        return convertToResource(organizerService.getOrganizerById(organizerId));
    }

    @PostMapping("/organizers")
    public OrganizerResource createOrganizer(@Valid @RequestBody SaveOrganizerResource saveOrganizerResource){
        return convertToResource(organizerService.createOrganizer(convertToEntity(saveOrganizerResource)));
    }
    @PutMapping("/organizers/{organizerId}")
    public OrganizerResource updateOrganizer(@PathVariable(name = "organizerId") Long organizerId,
                                              @RequestBody SaveOrganizerResource resource){
        return convertToResource(organizerService.updateOrganizer(organizerId, convertToEntity(resource)));
    }

    @DeleteMapping("/organizers/{organizerId}")
    public ResponseEntity<?> deleteOrganizer(@PathVariable(name = "organizerId") Long organizerId){
        return organizerService.deleteOrganizer(organizerId);
    }

    private Organizer convertToEntity(SaveOrganizerResource resource) {

        return mapper.map(resource, Organizer.class);
    }

    private OrganizerResource convertToResource(Organizer entity) {
        return mapper.map(entity, OrganizerResource.class);
    }
}
