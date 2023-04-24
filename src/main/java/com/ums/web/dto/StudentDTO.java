package com.ums.web.dto;

import com.ums.web.models.Subject;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class StudentDTO {
    Long id;
    String name;
    String surname;
    String albumId;
    Set<Subject> subjects;
}
