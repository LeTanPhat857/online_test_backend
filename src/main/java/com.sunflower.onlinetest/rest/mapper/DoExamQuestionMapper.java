package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.QuestionEntity;
import com.sunflower.onlinetest.rest.response.DoExamQuestionDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface DoExamQuestionMapper {

    @Mappings({
            @Mapping(target = "answers", ignore = true)
    })

    @AfterMapping
    default void afterMapping(QuestionEntity questionEntity, @MappingTarget DoExamQuestionDTO doExamQuestionDTO) {
        // map id to code for question
        doExamQuestionDTO.setCode(CustomBase64.encode(String.valueOf(questionEntity.getId())));
        // map id to code for answers
        DoExamAnswerMapper doExamAnswerMapper = new DoExamAnswerMapperImpl();
        doExamQuestionDTO.setAnswers(doExamAnswerMapper.entityToDTOs(questionEntity.getAnswers()));
    }

    DoExamQuestionDTO entityToDTO(QuestionEntity questionEntity);

    List<DoExamQuestionDTO> entityToDTOs(List<QuestionEntity> questionEntities);
}
