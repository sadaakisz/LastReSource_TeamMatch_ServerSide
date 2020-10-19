package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.model.Sponsor;
import com.teammatch.tournament.domain.service.PlayerService;
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

@Tag(name = "Player", description = "Player API")
@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    PlayerService playerService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping("/players")
    public Page<PlayerResource> getAllPlayers(Pageable pageable) {

        Page<Player> playersPage = playerService.getAllPlayers(pageable);
        List<PlayerResource> resources = playersPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/players/{playerId}")
    public PlayerResource getPlayerById(@PathVariable(value = "playerId") Long playerId) {
        return convertToResource(playerService.getPlayerById(playerId));
    }

    @PostMapping("/players")
    public PlayerResource createPlayer(
            @Valid @RequestBody SavePlayerResource resource) {
        Player player = convertToEntity(resource);
        return convertToResource(playerService.createPlayer(player));

    }

    @PutMapping("/players/{playerId}")
    public PlayerResource updatePlayer(@PathVariable Long playerId,
                                         @Valid @RequestBody SavePlayerResource resource) {
        Player player = convertToEntity(resource);
        return convertToResource(
                playerService.updatePlayer(playerId, player));
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long playerId) {
        return playerService.deletePlayer(playerId);
    }

    private Player convertToEntity(SavePlayerResource resource) {

        return mapper.map(resource, Player.class);
    }

    private PlayerResource convertToResource(Player entity) {
        return mapper.map(entity, PlayerResource.class);
    }
    
}
