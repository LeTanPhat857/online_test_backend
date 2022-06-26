package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.ExamEntity;
import com.sunflower.onlinetest.rest.response.DoExamDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface DoExamMapper {

    @Mappings({
            @Mapping(target = "topicName", ignore = true)
    })

    @AfterMapping
    default void afterMapping(ExamEntity examEntity, @MappingTarget DoExamDTO doExamDTO) {
        // map id to code for exam
        doExamDTO.setCode(CustomBase64.encode(String.valueOf(examEntity.getId())));
        // map topic name
        doExamDTO.setTopicName(examEntity.getTopic().getName());
        // map question list
        DoExamQuestionMapper doExamQuestionMapper = new DoExamQuestionMapperImpl();
        doExamDTO.setQuestions(doExamQuestionMapper.entityToDTOs(examEntity.getQuestions()));
    }

    DoExamDTO entityToDTO(ExamEntity examEntity);

    List<DoExamDTO> entityToDTOs(List<ExamEntity> examEntities);
}
