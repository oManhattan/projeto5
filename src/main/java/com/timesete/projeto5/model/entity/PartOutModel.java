package com.timesete.projeto5.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "part_out")
public class PartOutModel {
    
    private String id;
    private String userControl;
    private String userWithdrawal;
    private String partId;
    private LocalDateTime createdAt;
    private String type;
    private String reason;
    private String stage;
    private String session;
    private String carId;

}
