package com.finalproject.easyjob.repository;

import com.finalproject.easyjob.model.Message;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
  @Query("select m from Message m where :id in (m.user.id,m.otherUser.id) and :secondId in (m.otherUser.id,m.user.id)")
  List<Message> findConversation(Pageable pageable, @Param("id") int id,
                                 @Param("secondId") int secondId);
}
