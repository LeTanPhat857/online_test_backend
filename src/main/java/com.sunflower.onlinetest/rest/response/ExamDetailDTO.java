package com.sunflower.onlinetest.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExamDetailDTO {
    private String code;
    private String name;
    private Integer time;
    private String topicCode;
    private String topicName;
    private List<QuestionDTO> questions;
}
