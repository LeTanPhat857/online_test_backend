package com.sunflower.onlinetest.dao;

import com.sunflower.onlinetest.entity.QuestionEntity;

import java.util.List;

public class QuestionDAO extends GenericDAO<QuestionEntity> {

    public List<QuestionEntity> getAll() {
        return createTypeQuery("from QuestionEntity").getResultList();
    }

    public List<QuestionEntity> getAllByTopicId(Integer topicId) {
        try {
            return createTypeQuery("from QuestionEntity question where question.topic.id = :topicId")
                    .setParameter("topicId", topicId)
                    .getResultList();
        } catch (Exception exception) {
            return null;
        }
    }

    public QuestionEntity getQuestionByQuestionIdAndTopicId(Integer topicId, Integer questionId) {
        try {
            return createTypeQuery("from QuestionEntity question where question.id = :questionId and question.topic.id = :topicId")
                    .setParameter("topicId", topicId)
                    .setParameter("questionId", questionId)
                    .getSingleResult();
        } catch (Exception exception) {
            return null;
        }
    }
}
