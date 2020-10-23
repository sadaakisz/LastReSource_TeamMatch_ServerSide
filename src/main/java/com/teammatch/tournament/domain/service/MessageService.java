package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    Page<Message> getAllMessagesByChatId(Long chatId, Pageable pageable);
    Message getMessageByIdAndChatId(Long chatId, Long messageId);
    Message createMessage(Long chatId, Message message);
    Message updateMessage(Long chatId, Long messageId, Message messageRequest);
    ResponseEntity<?> deleteMessage(Long chatId, Long messageId);
}
