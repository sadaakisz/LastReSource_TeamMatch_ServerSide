package com.teammatch.tournament.domain.repository;

import com.teammatch.tournament.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    public Optional<Player>  findByLevel(Integer level);
    //Page<Player>  findByFilterId(Long filterId, Pageable pageable);
    //public Optional<Player>  findByIdAndFilterId(Long id, Long filterId);
}
