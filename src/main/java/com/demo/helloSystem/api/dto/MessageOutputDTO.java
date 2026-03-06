package com.demo.helloSystem.api.dto;

import com.demo.helloSystem.domain.entity.MessageEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

public record MessageOutputDTO (
        UUID id,
        String title,
        String message,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {

    public static MessageOutputDTO from(MessageEntity entity) {
        return new MessageOutputDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

}
