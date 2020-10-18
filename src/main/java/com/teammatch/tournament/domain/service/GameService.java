package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Game;
import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.model.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GameService {
    Page<Game> getAllGames(Pageable pageable);
    Game getGameById(Long gameId);
    Game createGame(Game game);
    Game updateGame(Long gameId, Game gameRequest);
    ResponseEntity<?> deleteGame(Long gameId);
    Game assignGamePlayer(Long gameId, Long playerId);
    Game unassignGamePlayer(Long gameId, Long playerId);
    Page<Game> getAllGamesByPlayerId(Long playerId, Pageable pageable);
    Game getPlayerByPlatform(String platform);
}
