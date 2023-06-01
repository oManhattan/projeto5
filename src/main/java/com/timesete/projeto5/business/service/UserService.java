package com.timesete.projeto5.business.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.business.converter.UserConverter;
import com.timesete.projeto5.business.util.Utilities;
import com.timesete.projeto5.model.dto.User.UserRequest;
import com.timesete.projeto5.model.dto.User.UserResponse;
import com.timesete.projeto5.model.entity.UserModel;
import com.timesete.projeto5.model.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private Utilities utilities;

    public UserResponse createUser(UserRequest request) throws Exception {

        if (userRepository.findUserByLogin(request.getLogin()).isPresent()) {
            throw new Exception("E-mail already registered");
        }

        if (!Utilities.validateEmail(request.getLogin())) {
            throw new Exception("E-mail is invalid");
        }

        if (!Utilities.validatePassword(request.getPassword())) {
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

        if (!model.getLogin().equals(request.getLogin()) && request.getLogin() != null) {
            Optional<UserModel> user = userRepository.findUserByLogin(request.getLogin());

            if (user.isPresent()) {
                throw new Exception("E-mail already registered");
            }

            if (!Utilities.validateEmail(request.getLogin())) {
                throw new Exception("E-mail is invalid");
            }
        }

        UserModel updateModel = UserModel.builder()
                .id(id)
                .name(utilities.getOrDefault(request.getName(), model.getName()))
                .login(utilities.getOrDefault(request.getLogin(), model.getLogin()))
                .accessType(utilities.getOrDefault(request.getAccessType(), model.getAccessType()))
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
        String id = getIdFromToken(token);
        return deleteUser(id);
    }

    public UserResponse updateUserByToken(String token, UserRequest request) throws Exception {
        String id = getIdFromToken(token);
        return updateUser(id, request);
    }

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserConverter::toUserResponse);
    }

    public UserResponse getUserByToken(String token) throws Exception {
        String id = getIdFromToken(token);
        return getUserById(id);
    }

    public UserResponse getUserById(String id) throws Exception {
        return UserConverter.toUserResponse(getModelById(id));
    }

    private UserModel getModelById(String id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new UsernameNotFoundException("Could not find user."));
    }

    private String getIdFromToken(String token) {
        return jwtDecoder.decode(token).getClaimAsString("id");
    }
}
