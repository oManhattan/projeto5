package com.timesete.projeto5.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "User")
public class UserModel {

    @Id
    private String id;
    private String name;
    private String login;
    private String password;
    private UserAccessType accessType;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
