package com.demo.helloSystem.api.controller;

import com.demo.helloSystem.api.dto.MessageInputDTO;
import com.demo.helloSystem.api.dto.MessageOutputDTO;
import com.demo.helloSystem.domain.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageOutputDTO> list(){
       return this.messageService.findAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageOutputDTO save(@RequestBody MessageInputDTO messageInputDTO){
        return this.messageService.create(messageInputDTO);
    }
}
