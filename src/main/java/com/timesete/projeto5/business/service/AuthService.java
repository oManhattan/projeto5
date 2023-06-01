package com.timesete.projeto5.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.business.converter.UserConverter;
import com.timesete.projeto5.model.dto.User.LoginRequest;
import com.timesete.projeto5.model.dto.User.UserResponse;
import com.timesete.projeto5.model.entity.UserModel;
import com.timesete.projeto5.model.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserAuthenticationProvider authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    public Pair<UserResponse, String> authenticateUser(LoginRequest request) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()));

        UserModel model = userRepository.findUserByLogin(request.getLogin())
                .orElseThrow(() -> new Exception("User not found"));

        String token = jwtService.generateToken(authentication, model.getId());

        return Pair.of(UserConverter.toUserResponse(model), token);

    }

}
