package com.teammatch.tournament.domain.repository;

import com.teammatch.tournament.domain.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long>{
    public Optional<Organizer>  findByUsername(String userName);

}
