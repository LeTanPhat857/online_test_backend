package com.sunflower.onlinetest.service;

import com.sunflower.onlinetest.entity.QuestionEntity;
import com.sunflower.onlinetest.rest.request.QuestionRequest;

import java.util.List;

public interface QuestionService {
    List<QuestionEntity> getAll();

    QuestionEntity getByCode(String code);

    QuestionEntity create(Integer userId, Integer topicId, QuestionRequest questionRequest);

    QuestionEntity update(String code, QuestionRequest questionRequest);

    QuestionEntity delete(String code);

    List<QuestionEntity> getAllByTopicId(Integer topicId);

    void checkTopicContainQuestion(Integer topicId, Integer questionId);
}
