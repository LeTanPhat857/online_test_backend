package com.sunflower.onlinetest.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TopicDTO {
    private String code;
    private String name;
    private String description;
    private LocalDate createdDate;
}
