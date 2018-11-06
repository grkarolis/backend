package com.program.backend.listeners;

import com.program.backend.services.NewsFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredListener {

    @Autowired
    private NewsFeedService newsFeedService;

    @EventListener
    public void onUserRegisteredEvent(com.program.backend.events.user.RegisterUserEvent event) {
        newsFeedService.addNewUserFeed(event.getUser().getId());
    }
}
