package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.repository.GameRepository;
import com.teammatch.tournament.domain.repository.PlayerRepository;
import com.teammatch.tournament.domain.service.PlayerService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Page<Player> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    @Override
    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Player", "Id", playerId));
    }

    @Override
    public Player createPlayer(Player player) { return playerRepository.save(player); }

    @Override
    public Player updatePlayer(Long playerId, Player playerRequest) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Player", "Id", playerId));
        return playerRepository.save(
                player.setLevel(playerRequest.getLevel())
                        .setHoursPlayed(playerRequest.getHoursPlayed())
        .setKillDeathRatio(playerRequest.getKillDeathRatio()));
    }

    @Override
    public ResponseEntity<?> deletePlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Player", "Id", playerId));
        playerRepository.delete(player);
        return ResponseEntity.ok().build();
    }


    @Override
    public Player getPlayerByLevel(Integer level) {
        return playerRepository.findByLevel(level)
                .orElseThrow(()->new ResourceNotFoundException("Player","Level",level));
    }
}
