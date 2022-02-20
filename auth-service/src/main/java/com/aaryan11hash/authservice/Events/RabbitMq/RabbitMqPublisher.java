package com.aaryan11hash.authservice.Events.RabbitMq;


import com.aaryan11hash.authservice.model.NotificationEmail;

public interface RabbitMqPublisher {

    void publishForEmailVerification(NotificationEmail notificationEmail);
}
