package com.sunflower.onlinetest.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TopicQuestionDTO {
    private String code;
    private String name;
    private String description;
    private LocalDate createdDate;
    List<QuestionDTO> questions;
}
