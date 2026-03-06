package com.demo.helloSystem.domain.service;

import com.demo.helloSystem.api.dto.MessageInputDTO;
import com.demo.helloSystem.api.dto.MessageOutputDTO;
import com.demo.helloSystem.domain.entity.MessageEntity;
import com.demo.helloSystem.domain.exception.BadRequestException;
import com.demo.helloSystem.domain.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageOutputDTO create(MessageInputDTO messageInputDTO){

        this.validateMessage(messageInputDTO);

        MessageEntity message = new MessageEntity(messageInputDTO.title(), messageInputDTO.message());
        this.messageRepository.saveAndFlush(message);
        return MessageOutputDTO.from(message);
    }

    public List<MessageOutputDTO> findAll(){
        List<MessageEntity> messages = this.messageRepository.findAll();
        return messages.stream().map(MessageOutputDTO::from).toList();
    }

    public void validateMessage(MessageInputDTO messageInputDTO){
        if(messageInputDTO == null){
            throw new BadRequestException("Received message is null");
        }

        if(messageInputDTO.title() == null || messageInputDTO.title().isBlank()){
            throw new BadRequestException("Received message title is null or blank");
        }

        if(messageInputDTO.message() == null || messageInputDTO.message().isBlank()){
            throw new BadRequestException("Received message content is null or blank");
        }

        if(messageInputDTO.title().length() > 100){
            throw new BadRequestException("Received message title is too long. Max length is 100 characters");
        }

        if(messageInputDTO.message().length() > 500){
            throw new BadRequestException("Received message content is too long. Max length is 500 characters");
        }
    }

}
