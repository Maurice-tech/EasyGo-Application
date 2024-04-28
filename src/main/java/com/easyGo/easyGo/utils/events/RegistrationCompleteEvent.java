package com.easyGo.easyGo.utils.events;

import com.easyGo.easyGo.entities.model.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private UserEntity user;
    private String applicationUrl;

    public RegistrationCompleteEvent(UserEntity user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
