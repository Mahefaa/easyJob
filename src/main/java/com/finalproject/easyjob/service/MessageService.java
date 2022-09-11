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
  private static final int ADMIN_MESSAGE_SENDER_ID = 0;
  private final MessageRepository repository;
  private final UserService userService;

  @Transactional
  public void saveMessage(int id, String content) {
    repository.save(
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
    return repository.findConversation(pageable, userId, ADMIN_MESSAGE_SENDER_ID);
  }
}
