package com.sunflower.onlinetest.dao;

import com.sunflower.onlinetest.entity.ResultEntity;

public class ResultDAO extends GenericDAO<ResultEntity> {

    public ResultEntity findByExamPersonIdAndExamId(Integer userId, Integer examId) {
        try {
//            return (ResultEntity) this.getEntityManager().createNativeQuery("select * from result where result.exam_id = ? and result.exam_person_id = ?")
//                    .setParameter(1, examId)
//                    .setParameter(2, userId)
//                    .getSingleResult();
            System.out.println("User id: " + userId);
            System.out.println("Exam id: " + examId);
            return createTypeQuery("select result from ResultEntity result join result.exam exam join result.examPerson examPerson where exam.id = :examId and examPerson.id = :userId")
                    .setParameter("userId", userId)
                    .setParameter("examId", examId)
                    .getSingleResult();
        } catch (Exception exception) {
            System.out.println("================================================================");
            exception.printStackTrace();
            return null;
        }
    }
}
