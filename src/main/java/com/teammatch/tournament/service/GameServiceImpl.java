package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Game;
import com.teammatch.tournament.domain.repository.GameRepository;
import com.teammatch.tournament.domain.service.GameService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Page<Game> getAllGames(Pageable pageable)
    {
        return gameRepository.findAll(pageable);
    }

    @Override
    public Game getGameById(Long gameId){
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Game", "Id", gameId));

    }

    @Override
    public Game createGame(Game game) { return gameRepository.save(game); }

    @Override
    public Game updateGame(Long gameId, Game gameRequest) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Game", "Id", gameId));
        return gameRepository.save(
                game.setName(gameRequest.getName())
                        .setPlatform(gameRequest.getPlatform())
                        .setMaxSquadMembers(gameRequest.getMaxSquadMembers()));
    }

    @Override
    public ResponseEntity<?> deleteGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Game", "Id", gameId));
        gameRepository.delete(game);
        return ResponseEntity.ok().build();
    }


    @Override
    public Game getGameByName(String name) {
        return gameRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Game","Name",name));
    }


}
