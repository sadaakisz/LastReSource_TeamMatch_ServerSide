package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Message;
import com.teammatch.tournament.domain.service.MessageService;
import com.teammatch.tournament.resource.MessageResource;
import com.teammatch.tournament.resource.SaveMessageResource;
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
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/chats/{chatId}/messages")
    public Page<MessageResource> getAllMessagesByChatId(
            @PathVariable(value = "chatId") Long chatId,
            Pageable pageable) {
        Page<Message> messages = messageService.getAllMessagesByChatId(chatId, pageable);
        List<MessageResource> resources = messages.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/chats/{chatId}/messages/{messageId}")
    public MessageResource getMessageByIdAndChatId(
            @PathVariable(name = "chatId") Long chatId,
            @PathVariable(name = "messageId") Long messageId) {
        return convertToResource(messageService.getMessageByIdAndChatId(chatId, messageId));
    }

    @PostMapping("/chats/{chatId}/messages")
    public MessageResource createMessage(@PathVariable(value = "chatId") Long chatId,
                                         @Valid @RequestBody SaveMessageResource resource) {

        return convertToResource(messageService.createMessage(chatId,  convertToEntity(resource)));
    }

    @PutMapping("/chats/{chatId}/messages/{messageId}")
    public MessageResource updateMessage(@PathVariable (value = "chatId") Long chatId,
                                         @PathVariable (value = "messageId") Long messageId,
                                         @Valid @RequestBody SaveMessageResource resource) {

        return convertToResource(messageService.updateMessage(chatId, messageId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/chats/{chatId}/messages/{messageId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable (value = "chatId") Long chatId,
            @PathVariable (value = "messageId") Long messageId) {
        return messageService.deleteMessage(chatId, messageId);
    }

    private Message convertToEntity(SaveMessageResource resource) {
        return mapper.map(resource, Message.class);
    }

    private MessageResource convertToResource(Message entity) {
        return mapper.map(entity, MessageResource.class);
    }

}

