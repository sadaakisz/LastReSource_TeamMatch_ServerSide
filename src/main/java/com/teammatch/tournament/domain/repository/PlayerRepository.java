package com.teammatch.tournament.domain.repository;

import com.teammatch.tournament.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    public Optional<Player>  findByLevel(Integer level);
}
