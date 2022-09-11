package com.finalproject.easyjob.controller;

import com.finalproject.easyjob.controller.mapper.MessageMapper;
import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.rest.RestMessage;
import com.finalproject.easyjob.service.MessageService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class MessageController {
  private final MessageService messageService;
  private final MessageMapper messageMapper;

  @GetMapping("/users/{userId}/messages")
  public List<RestMessage> getMessagesByUser(
      @RequestParam PageFromOne page,
      @RequestParam BoundedPageSize pageSize,
      @PathVariable("userId") int id) {
    return messageService.getMessages(page, pageSize, id).stream().map(messageMapper::toRest)
        .toList();
  }
}
