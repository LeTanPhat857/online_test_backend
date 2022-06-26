package com.sunflower.onlinetest.rest.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoExamRequest {

    private List<DoExamAnswerRequest> answers;
}
