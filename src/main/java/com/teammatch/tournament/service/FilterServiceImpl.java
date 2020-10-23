package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Filter;
import com.teammatch.tournament.domain.model.Game;
import com.teammatch.tournament.domain.repository.FilterRepository;
import com.teammatch.tournament.domain.repository.GameRepository;
import com.teammatch.tournament.domain.service.FilterService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class FilterServiceImpl implements FilterService{

    @Autowired
    private FilterRepository filterRepository;

    @Override
    public Page<Filter> getAllFilters(Pageable pageable)
    {
        return filterRepository.findAll(pageable);
    }

    @Override
    public Filter getFilterById(Long filterId){
        return filterRepository.findById(filterId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Filter", "Id", filterId));

    }

    @Override
    public Filter createFilter(Filter filter) { return filterRepository.save(filter); }

    @Override
    public Filter updateFilter(Long filterId, Filter filterRequest) {
        Filter filter = filterRepository.findById(filterId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Filter", "Id", filterId));
        return filterRepository.save(
                filter.setGame(filterRequest.getGame())
                        .setAge(filterRequest.getAge())
                        .setRegion(filterRequest.getRegion()))
                .setGameStyle(filterRequest.getGameStyle())
                .setAccountLevel(filterRequest.getAccountLevel())
                .setRating(filterRequest.getRating());
    }

    @Override
    public ResponseEntity<?> deleteFilter(Long filterId) {
        Filter filter = filterRepository.findById(filterId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Filter", "Id", filterId));
        filterRepository.delete(filter);
        return ResponseEntity.ok().build();
    }



}
