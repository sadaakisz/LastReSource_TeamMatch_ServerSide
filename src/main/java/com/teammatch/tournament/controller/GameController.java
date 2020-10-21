package com.teammatch.tournament.controller;


import com.teammatch.tournament.domain.model.Game;
import com.teammatch.tournament.domain.service.GameService;
import com.teammatch.tournament.resource.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Tag(name = "Game", description = "Game API")
@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    GameService gameService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping("/games")
    public Page<GameResource> getAllGames(Pageable pageable) {

        Page<Game> gamesPage = gameService.getAllGames(pageable);
        List<GameResource> resources = gamesPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/games/{gameId}")
    public GameResource getGameById(@PathVariable(value = "gameId") Long gameId) {
        return convertToResource(gameService.getGameById(gameId));
    }

    @PostMapping("/games")
    public GameResource createGame(
            @Valid @RequestBody SaveGameResource resource) {
        Game game = convertToEntity(resource);
        return convertToResource(gameService.createGame(game));

    }

    @PutMapping("/games/{gameId}")
    public GameResource updateGame(@PathVariable Long gameId,
                                       @Valid @RequestBody SaveGameResource resource) {
        Game game = convertToEntity(resource);
        return convertToResource(
                gameService.updateGame(gameId, game));
    }

    @DeleteMapping("/games/{gameId}")
    public ResponseEntity<?> deleteGame(@PathVariable Long gameId) {
        return gameService.deleteGame(gameId);
    }

    private Game convertToEntity(SaveGameResource resource) {

        return mapper.map(resource, Game.class);
    }

    private GameResource convertToResource(Game entity) {
        return mapper.map(entity, GameResource.class);
    }

}
