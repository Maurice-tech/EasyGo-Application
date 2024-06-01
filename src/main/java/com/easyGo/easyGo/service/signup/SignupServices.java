package com.easyGo.easyGo.service.signup;

import com.easyGo.easyGo.payload.request.DriverRequest;
import com.easyGo.easyGo.payload.request.UserRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface SignupServices {
    String register(UserRequest userRequest, HttpServletRequest request);
    String driverRegister(DriverRequest driverRequest, HttpServletRequest request);
}
