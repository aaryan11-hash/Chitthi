package com.aaryan11hash.chatservice.Repositories;



import com.aaryan11hash.chatservice.Web.Domain.ChatRoom;
import com.aaryan11hash.chatservice.Web.Model.ChatRoomDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
