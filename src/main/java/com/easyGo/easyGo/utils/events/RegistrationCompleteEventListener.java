//package com.easyGo.easyGo.utils.events;
//
//import com.easyGo.easyGo.entities.model.UserEntity;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//@Log4j2
//@Component
//@RequiredArgsConstructor
//public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
//    private final JWTGenerator jwtGenerator;
//    private final EmailServices emailServices;
//    private UserEntity theUser;
//
//    @Value("${frontend.url}")
//    private String frontEndUrl;
//
//    @Override
//    public void onApplicationEvent(RegistrationCompleteEvent event) {
//        theUser = event.getUser();
//        String verificationToken = jwtGenerator.
//
//    }
//}
