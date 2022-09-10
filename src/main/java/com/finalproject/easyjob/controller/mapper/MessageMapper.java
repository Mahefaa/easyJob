package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.Message;
import com.finalproject.easyjob.model.rest.RestMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageMapper {
  public RestMessage toRest(Message message) {
    return RestMessage.builder()
        .email(message.getUser().getEmail())
        .otherEmail(message.getOtherUser().getEmail())
        .content(message.getContent())
        .creationInstant(message.getCreationInstant())
        .build()
        ;
  }
}
