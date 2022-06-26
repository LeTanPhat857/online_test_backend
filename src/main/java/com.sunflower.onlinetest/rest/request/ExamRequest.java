package com.sunflower.onlinetest.rest.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExamRequest {

    private String name;

    private Integer time;

    private String topicCode;

    private List<String> questionCodes;
}
