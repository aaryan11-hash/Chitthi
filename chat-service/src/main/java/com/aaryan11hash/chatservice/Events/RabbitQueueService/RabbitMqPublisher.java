package com.aaryan11hash.chatservice.Events.RabbitQueueService;

import com.aaryan11hash.chatservice.Events.Models.BlobFileMessageEvent;

public interface RabbitMqPublisher {

    void publishBlobForProcess(BlobFileMessageEvent blobFileMessageEvent);
}
