package com.easyGo.easyGo.service.login_service.impl;

import com.easyGo.easyGo.entities.enums.RecordStatusConstant;
import com.easyGo.easyGo.entities.model.UserEntity;
import com.easyGo.easyGo.exceptions.*;
import com.easyGo.easyGo.payload.request.LoginRequest;
import com.easyGo.easyGo.payload.response.AuthResponse;
import com.easyGo.easyGo.repositories.UserRepository;
import com.easyGo.easyGo.security.JWTGenerator;
import com.easyGo.easyGo.service.login_service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;


    @Override
    public AuthResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        String path = request.getServletPath();

        UserEntity user = userRepo.findUserEntityByEmail(loginRequest.getEmail().toLowerCase())
                .orElseThrow(()-> new UserNotFoundException("Invalid Email address."));

        if(!path.contains(String.valueOf(user.getRoles()).toLowerCase() + 's')){
            throw new UnauthorizedException("You are not authorized") ;
        }

        if(path.contains("drivers") && loginRequest.getPassword().equals("111111")){
            throw new ChangeDefaultPasswordException("Please change the default password to continue");
        }

        if(path.contains("drivers") && user.getRecordStatus().equals(RecordStatusConstant.INACTIVE)){
            throw new UnauthorizedException("Your status is Inactive. Please contact the management");
        }

        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidPasswordException("Invalid password!");
        }

        if (!user.getIsVerified()){
            throw new UserNotVerifiedException("Not verified!");
        }

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (loginRequest.getEmail().toLowerCase(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication, 30L);
        String freshToken = jwtGenerator.generateToken(authentication, 1440L);

        return new AuthResponse(token, freshToken);
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
