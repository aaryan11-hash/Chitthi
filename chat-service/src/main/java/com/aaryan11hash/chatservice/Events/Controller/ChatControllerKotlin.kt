package com.aaryan11hash.chatservice.Events.Controller


import com.aaryan11hash.chatservice.Events.PubSubService.MessagePublisher
import com.aaryan11hash.chatservice.Events.RabbitQueueService.RabbitMqPublisher
import com.aaryan11hash.chatservice.Web.Service.BlobMessageService
import com.aaryan11hash.chatservice.Web.Service.ChatMessageService
import com.aaryan11hash.chatservice.Web.Service.ChatRoomService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.util.concurrent.ExecutorService

@Slf4j
@Controller
class ChatControllerKotlin {

    @Autowired
    lateinit var redisChatMessagePublisher : MessagePublisher

    @Autowired
    lateinit var chatMessageService: ChatMessageService

    @Autowired
    lateinit var chatRoomService: ChatRoomService

    @Autowired
    lateinit var blobMessageService: BlobMessageService

    @Autowired
    lateinit var  rabbitMqPublisher: RabbitMqPublisher

    @Autowired
    lateinit var inputOutputExec : ExecutorService



}