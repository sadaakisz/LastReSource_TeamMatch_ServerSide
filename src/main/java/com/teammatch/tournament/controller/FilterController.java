package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Filter;
import com.teammatch.tournament.domain.model.Game;
import com.teammatch.tournament.domain.repository.FilterRepository;
import com.teammatch.tournament.domain.service.FilterService;
import com.teammatch.tournament.resource.*;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Tag(name = "Filter", description = "Filter API")
@RestController
@RequestMapping("/api")
public class FilterController {
    @Autowired
    FilterService filterService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/filters")
    public Page<FilterResource> getAllFilters(Pageable pageable) {

        Page<Filter> filtersPage = filterService.getAllFilters(pageable);
        List<FilterResource> resources = filtersPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/filters/{filterId}")
    public FilterResource getFilterById(@PathVariable(value = "filterId") Long filterId) {
        return convertToResource(filterService.getFilterById(filterId));
    }

    @PostMapping("/filters")
    public FilterResource createFilter(
            @Valid @RequestBody SaveFilterResource resource) {
        Filter filter = convertToEntity(resource);
        return convertToResource(filterService.createFilter(filter));

    }

    @PutMapping("/filters/{filterId}")
    public FilterResource updateFilter(@PathVariable Long filterId,
                                   @Valid @RequestBody SaveFilterResource resource) {
        Filter filter = convertToEntity(resource);
        return convertToResource(
                filterService.updateFilter(filterId, filter));
    }

    @DeleteMapping("/filters/{filterId}")
    public ResponseEntity<?> deleteFilter(@PathVariable Long filterId)
    {
        return filterService.deleteFilter(filterId);
    }


    private Filter convertToEntity(SaveFilterResource resource) {

        return mapper.map(resource, Filter.class);
    }

    private FilterResource convertToResource(Filter entity) {
        return mapper.map(entity, FilterResource.class);
    }
}
