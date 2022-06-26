package com.sunflower.onlinetest.service.serviceImpl;

import com.sunflower.onlinetest.dao.ExamDAO;
import com.sunflower.onlinetest.dao.QuestionDAO;
import com.sunflower.onlinetest.dao.TopicDAO;
import com.sunflower.onlinetest.dao.UserDAO;
import com.sunflower.onlinetest.entity.ExamEntity;
import com.sunflower.onlinetest.entity.QuestionEntity;
import com.sunflower.onlinetest.entity.TopicEntity;
import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.rest.request.ExamRequest;
import com.sunflower.onlinetest.service.ExamService;
import com.sunflower.onlinetest.util.CustomBase64;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExamServiceImpl implements ExamService {

    @Inject
    private ExamDAO examDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private TopicDAO topicDAO;

    @Inject
    private QuestionDAO questionDAO;

    @Override
    public List<ExamEntity> getAllByUserId(Integer userId) {
        return examDAO.getAllByUserId(userId);
    }

    @Override
    public void checkUserOwnExam(Integer userId, Integer examId) {
        ExamEntity foundExam = examDAO.getExamByExamIdAndOwnerId(userId, examId);
        if (Objects.isNull(foundExam)) {
            throw new RuntimeException(String.format("User do not own exam, userId: %s and examId: %s", userId, examId));
        }
    }

    @Override
    public ExamEntity getByCode(String examCode) {
        try {
            Integer id = Integer.valueOf(CustomBase64.decode(examCode));
            return examDAO.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Could not get exam " + e.getMessage());
        }
    }

    @Override
    public ExamEntity create(Integer userId, ExamRequest examRequest) {
        try {
            UserEntity owner = getUserEntity(userId);
            TopicEntity foundTopic = getTopicEntity(examRequest);
            List<QuestionEntity> questionEntities = getQuestionEntities(examRequest);
            ExamEntity examEntity = ExamEntity.builder()
                    .name(examRequest.getName())
                    .time(examRequest.getTime())
                    .topic(foundTopic)
                    .questions(questionEntities)
                    .owner(owner)
                    .build();
            return examDAO.save(examEntity);
        } catch (Exception e) {
            throw new RuntimeException("Could not create exam " + e.getMessage());
        }
    }

    @Override
    public ExamEntity update(String examCode, ExamRequest examRequest) {
        try {
            ExamEntity foundExam = getExamEntity(examCode);
            TopicEntity foundTopic = getTopicEntity(examRequest);
            List<QuestionEntity> foundQuestionEntities = getQuestionEntities(examRequest);
            foundExam.setName(examRequest.getName());
            foundExam.setTime(examRequest.getTime());
            foundExam.setTopic(foundTopic);
            foundExam.setQuestions(foundQuestionEntities);
            return examDAO.update(foundExam);
        } catch (Exception e) {
            throw new RuntimeException("Could not update exam: " + e.getMessage());
        }
    }

    private ExamEntity getExamEntity(String code) {
        Integer id = Integer.valueOf(CustomBase64.decode(code));
        ExamEntity foundExam = examDAO.findById(id);
        if (Objects.isNull(foundExam)) {
            throw new RuntimeException("Exam could not found");
        }
        return foundExam;
    }

    @Override
    public ExamEntity delete(String code) {
        try {
            Integer id = Integer.valueOf(CustomBase64.decode(code));
            return examDAO.remove(id);
        } catch (Exception e) {
            throw new RuntimeException("Could not delete exam " + e.getMessage());
        }
    }

    private List<QuestionEntity> getQuestionEntities(ExamRequest examRequest) {
        return examRequest.getQuestionCodes().stream()
                .map(questionCode -> {
                    Integer questionId = CustomBase64.decodeAsInteger(questionCode);
                    QuestionEntity foundQuestion = questionDAO.findById(questionId);
                    if (Objects.isNull(foundQuestion)) {
                        throw new RuntimeException("Could not find question with id = " + questionCode);
                    }
                    return foundQuestion;
                })
                .collect(Collectors.toList());
    }

    private TopicEntity getTopicEntity(ExamRequest examRequest) {
        String topicCode = examRequest.getTopicCode();
        Integer topicId = CustomBase64.decodeAsInteger(topicCode);
        TopicEntity foundTopic = topicDAO.findById(topicId);
        if (Objects.isNull(foundTopic)) {
            throw new RuntimeException("Could not find topic with id = " + topicCode);
        }
        return foundTopic;
    }

    private UserEntity getUserEntity(Integer userId) {
        UserEntity owner = userDAO.findById(userId);
        if (Objects.isNull(owner)) {
            throw new RuntimeException("Could not find owner with id = " + CustomBase64.encode(String.valueOf(userId)));
        }
        return owner;
    }
}
