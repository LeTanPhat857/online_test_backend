package com.sunflower.onlinetest.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {
    private String content;
    private boolean rightAnswer;
//    private File attachedFile;
}
