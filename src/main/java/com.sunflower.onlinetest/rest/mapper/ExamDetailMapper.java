package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.ExamEntity;
import com.sunflower.onlinetest.rest.response.ExamDetailDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface ExamDetailMapper {

    @Mappings({
            @Mapping(target = "topicCode", ignore = true),
            @Mapping(target = "topicName", ignore = true)
    })

    @AfterMapping
    default void afterMapping(ExamEntity examEntity, @MappingTarget ExamDetailDTO examDetailDTO) {
        // map id to code for exam
        examDetailDTO.setCode(CustomBase64.encode(String.valueOf(examEntity.getId())));
        // map topic
        examDetailDTO.setTopicCode(CustomBase64.encode(String.valueOf(examEntity.getTopic().getId())));
        examDetailDTO.setTopicName(examEntity.getTopic().getName());
        // map question list
        QuestionMapper questionMapper = new QuestionMapperImpl();
        examDetailDTO.setQuestions(questionMapper.entityToDTOs(examEntity.getQuestions()));
    }

    ExamDetailDTO entityToDTO(ExamEntity examEntity);

    List<ExamDetailDTO> entityToDTOs(List<ExamEntity> examEntities);
}
