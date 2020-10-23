package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FilterService {
    Page<Filter> getAllFilters(Pageable pageable);
    Filter getFilterById(Long filterId);
    Filter createFilter(Filter filter);
    Filter updateFilter(Long filterId, Filter filterRequest);
    ResponseEntity<?> deleteFilter(Long filterId);
}
