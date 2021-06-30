package com.aaryan11hash.chatservice.Repositories;



import com.aaryan11hash.chatservice.Web.Model.ChatRoom;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends ReactiveMongoRepository<ChatRoom, String> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
