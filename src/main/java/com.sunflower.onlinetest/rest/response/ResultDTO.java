package com.sunflower.onlinetest.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResultDTO {

    private String examPersonName;

    private String topicName;

    private String examName;

    private Integer time;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int numOfQuestions;

    private int numOfRightAnswer;
}
