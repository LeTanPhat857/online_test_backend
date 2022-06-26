package com.sunflower.onlinetest.service;

import com.sunflower.onlinetest.entity.ExamEntity;
import com.sunflower.onlinetest.entity.ResultEntity;
import com.sunflower.onlinetest.rest.request.DoExamRequest;

public interface ResultService {

    ResultEntity create(Integer userId, ExamEntity examEntity);

    void checkSubmitPerson(Integer userId, Integer examId);

    ResultEntity update(Integer userId, Integer examId, DoExamRequest examRequest);
}
