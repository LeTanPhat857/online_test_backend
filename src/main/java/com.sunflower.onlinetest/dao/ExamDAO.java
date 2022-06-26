package com.sunflower.onlinetest.dao;

import com.sunflower.onlinetest.entity.ExamEntity;

import java.util.List;

public class ExamDAO extends GenericDAO<ExamEntity> {

    public List<ExamEntity> getAllByUserId(Integer userId) {
        try {
            return createTypeQuery("from ExamEntity exam where exam.owner.id = :userId")
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception exception) {
            return null;
        }
    }

    public ExamEntity getExamByExamIdAndOwnerId(Integer userId, Integer examId) {
        try {
            return createTypeQuery("from ExamEntity exam where exam.id = :examId and exam.owner.id = :userId")
                    .setParameter("userId", userId)
                    .setParameter("examId", examId)
                    .getSingleResult();
        } catch (Exception exception) {
            return null;
        }
    }
}
