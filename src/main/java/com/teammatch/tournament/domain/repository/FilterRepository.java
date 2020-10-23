package com.teammatch.tournament.domain.repository;

import com.teammatch.tournament.domain.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface FilterRepository extends JpaRepository<Filter, Long>{
}
