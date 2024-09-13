package com.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Entity.Chat;
import com.app.Entity.User;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    
    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = :userid")
    List<Chat> findChatByUserid(@Param("userid") Long userid);

    @Query("SELECT c FROM Chat c WHERE c.isGroup = false AND :user1 MEMBER OF c.users AND :user2 MEMBER OF c.users")
    Chat findSingleChatByUserIds(@Param("user1") User user1, @Param("user2") User user2);
}
