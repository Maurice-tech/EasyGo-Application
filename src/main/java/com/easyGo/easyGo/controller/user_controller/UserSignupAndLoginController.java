package com.easyGo.easyGo.controller.user_controller;

import com.easyGo.easyGo.payload.request.LoginRequest;
import com.easyGo.easyGo.payload.request.UserRequest;
import com.easyGo.easyGo.payload.response.ApiResponse;
import com.easyGo.easyGo.payload.response.AuthResponse;
import com.easyGo.easyGo.service.login_service.LoginService;
import com.easyGo.easyGo.service.signup.SignupServices;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserSignupAndLoginController {
    private final SignupServices signupServices;
    private final LoginService loginService;

    @Operation(
            description = "Get end point for SignUp",
            summary = "user will be able to SignUp",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "201"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    ),

            }
    )

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> registration
            (@Valid @RequestBody UserRequest userRequest, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(signupServices.register(userRequest, request)));
    }


    @Operation(
            description = "Get end point for LogIn",
            summary = "user will be able to LogIn",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login
            (@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>("Login successfully", loginService.login(loginRequest, request)));
    }
    @Operation(
            description = "Get end point for LogOut",
            summary = "user will be able to LogOut",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/logout")
    private ResponseEntity<ApiResponse<String>> logout(){
        loginService.logout();
        return ResponseEntity.ok(new ApiResponse<>("Logout Successfully"));
    }



}
