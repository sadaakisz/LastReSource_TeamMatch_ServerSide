package com.teammatch.tournament.domain.repository;

import com.teammatch.tournament.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long > {
    public Optional<Team> findByName(String name);

}
