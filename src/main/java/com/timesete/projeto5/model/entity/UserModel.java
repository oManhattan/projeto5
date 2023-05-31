package com.timesete.projeto5.model.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @MongoId
    private Long id;
    private String name;
    private String login;
    private String password;
    private String accessType;
}
