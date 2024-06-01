package com.easyGo.easyGo.service.login_service;

import com.easyGo.easyGo.payload.request.LoginRequest;
import com.easyGo.easyGo.payload.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {
    AuthResponse login(LoginRequest loginRequest, HttpServletRequest request);

    void logout();
}
