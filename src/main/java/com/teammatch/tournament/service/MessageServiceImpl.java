package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Message;
import com.teammatch.tournament.domain.repository.ChatRepository;
import com.teammatch.tournament.domain.repository.MessageRepository;
import com.teammatch.tournament.domain.service.MessageService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Page<Message> getAllMessagesByChatId(Long chatId, Pageable pageable) {
        return messageRepository.findByChatId(chatId, pageable);
    }

    @Override
    public Message getMessageByIdAndChatId(Long chatId, Long messageId) {
        return messageRepository.findByIdAndChatId(messageId, chatId)
                .orElseThrow(() -> new ResourceNotFoundException( "Message not found with Id " + messageId + " and ChatId " + chatId));
    }

    @Override
    public Message createMessage(Long chatId, Message message) {
        return chatRepository.findById(chatId).map(chat -> {
            message.setChat(chat);
            return messageRepository.save(message);
        }).orElseThrow(() -> new ResourceNotFoundException( "Chat", "Id", chatId));
    }

    @Override
    public Message updateMessage(Long chatId, Long messageId, Message messageRequest) {
        if(!chatRepository.existsById(chatId))
            throw new ResourceNotFoundException("Chat" + "Id" + chatId);

        return messageRepository.findById(messageId).map(message -> {
            message.setContent(messageRequest.getContent());
            message.setHyperLink(messageRequest.getHyperLink());
            return messageRepository.save(message);
        }).orElseThrow(()-> new ResourceNotFoundException("Message" + "Id" + messageId ));
    }

    @Override
    public ResponseEntity<?> deleteMessage(Long chatId, Long messageId) {
        return messageRepository.findByIdAndChatId(messageId, chatId).map(message -> {
            messageRepository.delete(message);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Message not found with Id " + messageId + " and ChatId " + chatId));
    }
}
