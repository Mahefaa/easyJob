package com.finalproject.easyjob.service;

import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.Message;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.repository.MessageRepository;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {
  private final MessageRepository repository;
  private final UserService userService;

  @Transactional
  public Message saveMessage(int id, String content) {
    return repository.save(
        Message.builder()
            .user(userService.getById(id))
            .otherUser(userService.getById(0))
            .content(content)
            .creationInstant(Instant.now(Clock.system(ZoneId.of("GMT+3"))))
            .build()
    );
  }

  public List<Message> getMessages(PageFromOne page, BoundedPageSize pageSize, int userId) {
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue(), Sort.by(
        Sort.Direction.DESC, "creationInstant"));
    //0 is Admin's id
    return repository.findConversation(pageable, userId, 0);
  }
}
