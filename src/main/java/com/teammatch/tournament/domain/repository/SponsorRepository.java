package com.teammatch.tournament.domain.repository;

import com.teammatch.tournament.domain.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SponsorRepository extends JpaRepository<Sponsor, Long > {
    public Optional<Sponsor> findByName(String name);
}
