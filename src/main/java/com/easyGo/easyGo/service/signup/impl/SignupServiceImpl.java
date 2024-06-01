package com.easyGo.easyGo.service.signup.impl;

import com.easyGo.easyGo.entities.enums.Gender;
import com.easyGo.easyGo.entities.enums.Roles;
import com.easyGo.easyGo.entities.model.UserEntity;
import com.easyGo.easyGo.exceptions.DuplicateEmailException;
import com.easyGo.easyGo.exceptions.PasswordMismatchException;
import com.easyGo.easyGo.payload.request.DriverRequest;
import com.easyGo.easyGo.payload.request.UserRequest;
import com.easyGo.easyGo.repositories.UserRepository;
import com.easyGo.easyGo.service.signup.SignupServices;

import com.easyGo.easyGo.utils.events.RegistrationCompleteEvent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupServices {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public String register(UserRequest userRequest, HttpServletRequest request) {
        if (userRepository.existsByEmail(userRequest.getEmail().toLowerCase())) {
            throw new DuplicateEmailException("Email already exist");
        }
        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            throw new PasswordMismatchException("Password mismatch");
        }
        String path = request.getServletPath();
        UserEntity user = UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail().toLowerCase())
                .password(encoder.encode(userRequest.getPassword()))
                .confirmPassword(userRequest.getConfirmPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .address(userRequest.getAddress())
                .gender(Gender.valueOf(userRequest.getGender().toUpperCase()))
                .dob(userRequest.getDob())
                .roles(
                        path.contains("users") ? Roles.USER : Roles.ADMIN
                )
                .isVerified(false)
                .build();
        userRepository.save(user);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Verification link sent to Email. Check email and verify your account.";

    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

    @Override
    public String driverRegister(DriverRequest driverRequest, HttpServletRequest request) {

        return null;
    }
}
