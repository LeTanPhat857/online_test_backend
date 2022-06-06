package com.sunflower.onlinetest.service;

import com.sunflower.onlinetest.entity.TopicEntity;
import com.sunflower.onlinetest.rest.request.TopicRequest;
import com.sunflower.onlinetest.rest.response.TopicDTO;

import java.util.List;

public interface TopicService {

    List<TopicEntity> getAll();

    TopicEntity create(Integer userId, TopicRequest topicRequest);

    TopicEntity getByCode(String code);

    TopicEntity update(String code, TopicRequest topicRequest);

    TopicEntity delete(String code);

    List<TopicEntity> getAllByUserId(Integer userId);

    void checkUserOwnTopic(Integer userId, Integer topicId);
}
