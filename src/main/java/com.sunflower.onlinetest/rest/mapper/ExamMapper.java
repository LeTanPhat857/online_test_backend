package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.ExamEntity;
import com.sunflower.onlinetest.rest.response.ExamDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface ExamMapper {

    @Mappings({
            @Mapping(target = "topicName", ignore = true),
            @Mapping(target = "questionNum", ignore = true)
    })

    @AfterMapping
    default void afterMapping(ExamEntity examEntity, @MappingTarget ExamDTO examDTO) {
        // map id to code for exam
        examDTO.setCode(CustomBase64.encode(String.valueOf(examEntity.getId())));
        // map topic name
        examDTO.setTopicName(examEntity.getTopic().getName());
        // map question number
        examDTO.setNumOfQuestion(examEntity.getQuestions().size());
    }

    ExamDTO entityToDTO(ExamEntity examEntity);

    List<ExamDTO> entityToDTOs(List<ExamEntity> examEntities);
}
