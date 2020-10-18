package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PlayerService {
    Page<Player> getAllOPlayers(Pageable pageable);
    Player getPlayerById(Long playerId);
    Player createPlayer(Player player);
    Player updatePlayer(Long playerId, Player playerRequest);
    ResponseEntity<?> deletePlayer(Long playerId);
    Player getPlayerByLevel(Integer level);
}
