package com.timesete.projeto5.business.service;

import com.timesete.projeto5.business.converter.UserConverter;
import com.timesete.projeto5.business.util.RegexUtil;
import com.timesete.projeto5.model.dto.LoginRequest;
import com.timesete.projeto5.model.dto.UserRequest;
import com.timesete.projeto5.model.dto.UserResponse;
import com.timesete.projeto5.model.entity.UserModel;
import com.timesete.projeto5.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public UserResponse createUser(UserRequest request) throws Exception {

        if (userRepository.findUserByLogin(request.getLogin()).isPresent()) {
            throw new Exception("E-mail already registered");
        }

        if (!RegexUtil.validateEmail(request.getLogin())) {
            throw new Exception("E-mail is invalid");
        }

        if (!RegexUtil.validatePassword(request.getPassword())) {
            throw new Exception("Password does not conform to the minimum specified");
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encryptedPassword);

        UserModel model = UserConverter.toUserModel(request);
        model.setCreatedAt(LocalDateTime.now());
        UserResponse response = UserConverter.toUserResponse(userRepository.insert(model));

        return response;
    }

    public UserResponse updateUser(String id, UserRequest request) throws Exception {
        UserModel model = getModelById(id);

        if (!model.getLogin().equals(request.getLogin())) {
            Optional<UserModel> user = userRepository.findUserByLogin(request.getLogin());

            if (user.isPresent()) {
                throw new Exception("E-mail already registered");
            }

            if (!RegexUtil.validateEmail(request.getLogin())) {
                throw new Exception("E-mail is invalid");
            }
        }

        UserModel updateModel = UserModel.builder()
                .id(id)
                .name(request.getName().isEmpty() ? model.getName() : request.getName())
                .login(request.getLogin().isEmpty() ? model.getLogin() : request.getLogin())
                .accessType(request.getAccessType() == null ? model.getAccessType() : request.getAccessType())
                .password(null)
                .lastModifiedAt(LocalDateTime.now())
                .build();

        Integer updateCount = userRepository.updateUserById(id, updateModel);

        if (updateCount <= 0) {
            throw new Exception("Failed to update user.");
        }

        return UserConverter.toUserResponse(updateModel);
    }

    public Boolean deleteUser(String id) throws Exception {
        UserModel model = getModelById(id);
        userRepository.delete(model);
        return true;
    }

    public Boolean deleteUserByToken(String token) throws Exception {
        String id = tokenService.getTokenId(token);
        return deleteUser(id);
    }

    public UserResponse updateUserByToken(String token, UserRequest request) throws Exception {
        String id = tokenService.getTokenId(token);
        return updateUser(id, request);
    }

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserConverter::toUserResponse);
    }

    public Pair<UserResponse, String> authenticateUser(LoginRequest request) throws Exception {

        UserModel model = getModelByLogin(request.getLogin());

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = tokenService.generateToken(auth, model.getId());

        return Pair.of(UserConverter.toUserResponse(model), token);

    }

    public UserResponse getUserByToken(String token) throws Exception {
        String id = tokenService.getTokenId(token);
        return getUserById(id);
    }

    public UserResponse getUserById(String id) throws Exception {
        return UserConverter.toUserResponse(getModelById(id));
    }

    private UserModel getModelById(String id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new UsernameNotFoundException("Could not find user."));
    }

    private UserModel getModelByLogin(String login) throws Exception {
        return userRepository.findUserByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user."));
    }
}
