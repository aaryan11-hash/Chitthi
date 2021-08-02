package com.chitthi.authservice.RabbitQueueService;


import com.chitthi.authservice.model.NotificationEmail;

public interface RabbitMqPublisher {

    public void userSignUpMailEvent(NotificationEmail notificationEmail);
}
