package com.sunflower.onlinetest.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DoExamDTO {
    private String code;
    private String name;
    private LocalDateTime startTime;
    private Integer time;
    private String topicName;
    private List<DoExamQuestionDTO> questions;
}
