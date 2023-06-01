package com.timesete.projeto5.controller;

import com.timesete.projeto5.business.service.AuthService;
import com.timesete.projeto5.model.dto.User.LoginRequest;
import com.timesete.projeto5.model.dto.User.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService loginService;

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody LoginRequest request) {
        try {
            Pair<UserResponse, String> pair = loginService.authenticateUser(request);
            return ResponseEntity
                    .ok()
                    .header("Authorization", pair.getSecond())
                    .body(pair.getFirst());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(400)
                    .body(String.format("%s\n%s", e.getMessage(), e.getStackTrace().toString()));

        }
    }
}
