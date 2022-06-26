package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.ResultEntity;
import com.sunflower.onlinetest.rest.response.ResultDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface ResultMapper {

    @AfterMapping
    default void afterMapping(ResultEntity resultEntity, @MappingTarget ResultDTO resultDTO) {
        resultDTO.setNumOfQuestions(resultEntity.getExam().getQuestions().size());
        resultDTO.setExamPersonName(resultEntity.getExamPerson().getFullName());
        resultDTO.setExamName(resultEntity.getExam().getName());
        resultDTO.setTime(resultEntity.getExam().getTime());
        resultDTO.setNumOfRightAnswer(resultEntity.getNumOfRightAnswer());
        resultDTO.setTopicName(resultEntity.getExam().getTopic().getName());
    }

    ResultDTO entityToDTO(ResultEntity resultEntity);

    List<ResultDTO> entityToDTOs(List<ResultEntity> resultEntity);
}
