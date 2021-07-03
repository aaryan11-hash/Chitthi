package com.aaryan11hash.chatservice.Events.RabbitQueueService;

import com.aaryan11hash.chatservice.Web.Model.BlobFileMessage;
import reactor.core.publisher.Mono;

public interface RabbitMqPublisher {

    void publishBlobForProcess(BlobFileMessage blobFileMessage);
}
