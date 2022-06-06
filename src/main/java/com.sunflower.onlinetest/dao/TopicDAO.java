package com.sunflower.onlinetest.dao;

import com.sunflower.onlinetest.entity.TopicEntity;

import java.util.List;

public class TopicDAO extends GenericDAO<TopicEntity> {

    public List<TopicEntity> getAll() {
        return createTypeQuery("from TopicEntity").getResultList();
    }

    public List<TopicEntity> getAllByUserId(Integer userId) {
        try {
            return createTypeQuery("from TopicEntity topic where topic.owner.id = :userId")
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception exception) {
            return null;
        }
    }

    public TopicEntity getTopicByTopicIdAndOwnerId(Integer userId, Integer topicId) {
        try {
            return createTypeQuery("from TopicEntity topic where topic.id = :topicId and topic.owner.id = :userId")
                    .setParameter("userId", userId)
                    .setParameter("topicId", topicId)
                    .getSingleResult();
        } catch (Exception exception) {
            return null;
        }
    }
}
