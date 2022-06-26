package com.sunflower.onlinetest.rest.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoExamAnswerRequest {

    private String questionCode;

    private List<String> answerCodes;
}
