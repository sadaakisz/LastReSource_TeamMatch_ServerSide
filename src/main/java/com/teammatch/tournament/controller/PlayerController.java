package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.FreeTournament;
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

    /*@GetMapping("/filters/{filterId}/players")
    public Page<Player> getAllPlayersByFilterId(@PathVariable(value = "filterId") Long filterId, Pageable pageable){
        Page<Player> playerPage= playerService.getAllPlayersByFilterId(filterId, pageable);
        List<PlayerResource> resources = playerPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl(resources, pageable, resources.size());
    }*/

    /*@GetMapping("/filters/{filterId}/players")
    public Page<PlayerResource> getAllPlayersByFilterId(
            @PathVariable(name = "filterId") Long filterId,
            Pageable pageable) {
        List<PlayerResource> players = playerService.getAllPlayersByFreeTournamentId(filterId,pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int playersCount = players.size();
        return new PageImpl<>(players, pageable, playersCount);
    }*/

    /*@GetMapping("/filters/{filterId}/players/{playerId}")
    public PlayerResource getPlayerByIdAndFilterId(@PathVariable(value = "filterId") Long filterId, @PathVariable (value = "playerId") Long playerId){
        return convertToResource(playerService.getPlayerByIdAndFilterId(filterId, playerId));
    }*/

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

    @GetMapping("/chats/{chatId}/players")
    public Page<PlayerResource> getAllPlayersByChatId(@PathVariable(name = "chatId") Long chatId, Pageable pageable){
        Page<Player> playerPage = playerService.getAllPlayersByChatId(chatId, pageable);
        List<PlayerResource> resources = playerPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/players/{playerId}/chats/{chatId}")
    public PlayerResource addPlayerToChat(@PathVariable(name = "playerId") Long playerId,
                                      @PathVariable(name = "chatId") Long chatId){
        return  convertToResource(playerService.addPlayerToChat(playerId, chatId));
    }

    @DeleteMapping("/players/{playerId}/chats/{chatId}")
    public PlayerResource deletePlayerFromChat(@PathVariable(name = "playerId") Long playerId,
                                        @PathVariable(name = "chatId") Long chatId){
        return  convertToResource(playerService.deletePlayerFromChat(playerId, chatId));
    }



    private Player convertToEntity(SavePlayerResource resource) { return mapper.map(resource, Player.class);  }

    private PlayerResource convertToResource(Player entity) {
        return mapper.map(entity, PlayerResource.class);
    }
    
}
