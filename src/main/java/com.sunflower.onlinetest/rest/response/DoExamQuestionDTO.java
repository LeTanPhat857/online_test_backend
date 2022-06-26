package com.sunflower.onlinetest.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoExamQuestionDTO {
    private String code;
    private String content;
    private List<DoExamAnswerDTO> answers;
}
