package com.ums.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentDTO {
    Long id;
    String name;
    String surname;
    String albumId;

}
