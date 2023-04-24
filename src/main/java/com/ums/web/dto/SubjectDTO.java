package com.ums.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class SubjectDTO {
    private Long id;
    private String name;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
