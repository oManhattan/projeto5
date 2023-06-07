package com.timesete.projeto5.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "part_entry")
public class PartEntryModel {
    
    private String id;
    private String userId;
    private String partId;
    private String type;
    private String reason;
    private LocalDateTime createdAt;

}
