package com.sunflower.onlinetest.service.serviceImpl;

import com.sunflower.onlinetest.dao.TopicDAO;
import com.sunflower.onlinetest.dao.UserDAO;
import com.sunflower.onlinetest.entity.TopicEntity;
import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.rest.mapper.TopicMapper;
import com.sunflower.onlinetest.rest.request.TopicRequest;
import com.sunflower.onlinetest.service.TopicService;
import com.sunflower.onlinetest.util.CustomBase64;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

public class TopicServiceImpl implements TopicService {

    @Inject
    private TopicDAO topicDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private TopicMapper topicMapper;

    @Override
    public List<TopicEntity> getAll() {
        return topicDAO.getAll();
    }

    @Override
    public TopicEntity getByCode(String code) {
        try {
            Integer id = Integer.valueOf(CustomBase64.decode(code));
            return topicDAO.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Could not get topic " + e.getMessage());
        }
    }

    @Override
    public TopicEntity create(Integer userId, TopicRequest topicRequest) {
        try {
            UserEntity owner = userDAO.findById(userId);
            if (Objects.isNull(owner)) {
                throw new RuntimeException("Could not find owner with id = " + userId);
            }
            TopicEntity topicEntity = TopicEntity.builder()
                    .name(topicRequest.getName())
                    .description(topicRequest.getDescription())
                    .owner(owner)
                    .build();
            return topicDAO.save(topicEntity);
        } catch (Exception e) {
            throw new RuntimeException("Could not create topic " + e.getMessage());
        }
    }

    @Override
    public TopicEntity update(String code, TopicRequest topicRequest) {
        try {
            Integer id = Integer.valueOf(CustomBase64.decode(code));
            TopicEntity foundTopic = topicDAO.findById(id);
            if (Objects.isNull(foundTopic)) {
                throw new RuntimeException("Topic could not found");
            }
            foundTopic.setName(topicRequest.getName());
            foundTopic.setDescription(topicRequest.getDescription());
            return topicDAO.update(foundTopic);
        } catch (Exception e) {
            throw new RuntimeException("Could not update topic: " + e.getMessage());
        }
    }

    @Override
    public TopicEntity delete(String code) {
        try {
            Integer id = Integer.valueOf(CustomBase64.decode(code));
            return topicDAO.remove(id);
        } catch (Exception e) {
            throw new RuntimeException("Could not delete topic " + e.getMessage());
        }
    }

    @Override
    public List<TopicEntity> getAllByUserId(Integer userId) {
        return topicDAO.getAllByUserId(userId);
    }

    @Override
    public void checkUserOwnTopic(Integer userId, Integer topicId) {
        TopicEntity foundTopic = topicDAO.getTopicByTopicIdAndOwnerId(userId, topicId);
        if (Objects.isNull(foundTopic)) {
            throw new RuntimeException(String.format("User do not own topic, userId: %s and topicId: %s", userId, topicId));
        }
    }
}
