package com.sunflower.onlinetest.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
    private String code;
    private String content;
    private boolean rightAnswer;
}
