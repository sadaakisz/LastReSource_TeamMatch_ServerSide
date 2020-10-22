package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Chat;
import com.teammatch.tournament.domain.service.ChatService;
import com.teammatch.tournament.resource.ChatResource;
import com.teammatch.tournament.resource.SaveChatResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    ChatService chatService;

    @GetMapping("/chats")
    public Page<ChatResource> getAllChats(Pageable pageable) {
        List<ChatResource> resources = chatService.getAllChats(pageable).getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @GetMapping("/players/{playerId}/chats")
    public Page<ChatResource> getChatsByPlayerId(@PathVariable(name = "playerId") Long playerId, Pageable pageable){
        List<ChatResource> chats = chatService.getAllChatsByPlayerId(playerId, pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(chats ,pageable, chats.size());
    }

    @GetMapping("/chats/{chatId}")
    public ChatResource getChatById(@PathVariable(name = "chatId") Long chatId){
        return convertToResource(chatService.getChatById(chatId));
    }

    @PostMapping("/chats")
    public ChatResource createChat(@Valid @RequestBody SaveChatResource chatResource){
        return convertToResource(chatService.createChat(convertToEntity(chatResource)));
    }

    @DeleteMapping("/chats/{chatId}")
    public ResponseEntity<?> deleteChat(@PathVariable(name = "chatId") Long chatId){
        return chatService.deleteChat(chatId);
    }

    private Chat convertToEntity(SaveChatResource resource){ return mapper.map(resource, Chat.class);   }
    private ChatResource convertToResource(Chat entity){return mapper.map(entity, ChatResource.class);  }

}
