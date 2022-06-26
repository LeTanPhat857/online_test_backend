package com.sunflower.onlinetest.service;

import com.sunflower.onlinetest.entity.ExamEntity;
import com.sunflower.onlinetest.rest.request.ExamRequest;

import java.util.List;

public interface ExamService {

    List<ExamEntity> getAllByUserId(Integer userId);

    void checkUserOwnExam(Integer userId, Integer examId);

    ExamEntity getByCode(String examCode);

    ExamEntity create(Integer userId, ExamRequest examRequest);

    ExamEntity update(String code, ExamRequest examRequest);

    ExamEntity delete(String code);
}
