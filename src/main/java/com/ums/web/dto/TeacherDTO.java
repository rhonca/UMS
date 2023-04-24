package com.ums.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class TeacherDTO {
    String name;
    String surname;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
