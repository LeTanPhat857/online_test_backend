package com.sunflower.onlinetest.service.serviceImpl;

import com.sunflower.onlinetest.dao.*;
import com.sunflower.onlinetest.entity.*;
import com.sunflower.onlinetest.rest.request.DoExamAnswerRequest;
import com.sunflower.onlinetest.rest.request.DoExamRequest;
import com.sunflower.onlinetest.service.ResultService;
import com.sunflower.onlinetest.util.CustomBase64;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResultServiceImpl implements ResultService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private ResultDAO resultDAO;

    @Inject
    private ExamDAO examDAO;

    @Inject
    private QuestionDAO questionDAO;

    @Inject
    private AnswerDAO answerDAO;

    @Override
    public ResultEntity create(Integer userId, ExamEntity examEntity) {
        try {
            UserEntity examPerson = userDAO.findById(userId);
            if (Objects.isNull(examPerson)) {
                throw new RuntimeException("Could not find exam person with id = " + userId);
            }
            ResultEntity resultEntity = resultDAO.findByExamPersonIdAndExamId(userId, examEntity.getId());
            if (Objects.isNull(resultEntity)) {
                resultEntity = ResultEntity.builder()
                        .exam(examEntity)
                        .examPerson(examPerson)
                        .startTime(LocalDateTime.now())
                        .build();
                return resultDAO.save(resultEntity);
            } else {
                return resultEntity;
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not create result " + e.getMessage());
        }
    }

    @Override
    public ResultEntity update(Integer userId, Integer examId, DoExamRequest examRequest) {
        try {
            ResultEntity foundResult = resultDAO.findByExamPersonIdAndExamId(userId, examId);
            foundResult.setNumberOfDoingTheExam(foundResult.getNumberOfDoingTheExam() + 1);
            foundResult.getSubmittedResults().clear();
            foundResult.getSubmittedResults().addAll(convert(examRequest.getAnswers()));
            foundResult.setNumOfRightAnswer(compute(foundResult.getExam().getQuestions(), examRequest.getAnswers()));
            foundResult.setEndTime(LocalDateTime.now());
            return resultDAO.update(foundResult);
        } catch (Exception e) {
            throw new RuntimeException("Could not update result: " + e.getMessage());
        }
    }

    @Override
    public void checkSubmitPerson(Integer userId, Integer examId) {
        ResultEntity resultEntity = resultDAO.findByExamPersonIdAndExamId(userId, examId);
        if (Objects.isNull(resultEntity)) {
            throw new RuntimeException("User must take exam before submitting");
        }
    }

    private List<SubmittedResultEntity> convert(List<DoExamAnswerRequest> answers) {
        return answers.stream()
                .map(doExamAnswerRequest -> {
                    // get question entity
                    String questionCode = doExamAnswerRequest.getQuestionCode();
                    QuestionEntity questionEntity = questionDAO.findById(CustomBase64.decodeAsInteger(questionCode));
                    // get answer entity
                    List<AnswerEntity> answerEntities = doExamAnswerRequest.getAnswerCodes().stream()
                            .map(answerCode -> answerDAO.findById(CustomBase64.decodeAsInteger(answerCode)))
                            .collect(Collectors.toList());
                    // build submittedAnswerEntity
                    return SubmittedResultEntity.builder()
                            .question(questionEntity)
                            .answers(answerEntities)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private int compute(List<QuestionEntity> questions, List<DoExamAnswerRequest> answers) {
        int result = 0;
        for (QuestionEntity question : questions) {
            for (DoExamAnswerRequest answer : answers) {
                Integer questionId = CustomBase64.decodeAsInteger(answer.getQuestionCode());
                if (questionId.equals(question.getId()) && compareAnswers(question.getAnswers(), answer.getAnswerCodes())) {
                    result += 1;
                }
            }
        }
        return result;
    }

    private boolean compareAnswers(List<AnswerEntity> answers, List<String> answerCodes) {
        List<AnswerEntity> rightAnswers = answers.stream().filter(AnswerEntity::isRightAnswer).collect(Collectors.toList());
        if (rightAnswers.size() != answerCodes.size()) {
            return false;
        }
        for (AnswerEntity answerEntity : rightAnswers) {
            String answerCode = CustomBase64.encode(String.valueOf(answerEntity.getId()));
            if (!answerCodes.contains(answerCode)) {
                return false;
            }
        }
        return true;
    }
}
