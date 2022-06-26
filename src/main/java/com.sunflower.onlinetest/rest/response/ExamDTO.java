package com.sunflower.onlinetest.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamDTO {
    private String code;
    private String name;
    private Integer time;
    private String topicName;
    private Integer numOfQuestion;
}
