package com.aaryan11hash.chatservice.Events.RabbitQueueService;

import com.aaryan11hash.chatservice.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chatservice.Web.Model.BlobFileMessageDto;

public interface RabbitMqPublisher {

    void publishBlobForProcess(BlobFileMessageEvent blobFileMessageEvent);
}
