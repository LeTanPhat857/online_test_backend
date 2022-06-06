package com.sunflower.onlinetest.rest.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionRequest {
    private String content;
//    private File attachedFile;
    private List<AnswerRequest> answers;
//    private List<AnswerRequest> rightAnswers;
}
