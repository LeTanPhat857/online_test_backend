package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.QuestionEntity;
import com.sunflower.onlinetest.rest.response.QuestionDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Mappings({
            @Mapping(target = "answers", ignore = true)
    })

    @AfterMapping
    default void afterMapping(QuestionEntity questionEntity, @MappingTarget QuestionDTO questionDTO) {
        // map id to code for question
        questionDTO.setCode(CustomBase64.encode(String.valueOf(questionEntity.getId())));
        // map id to code for answers
        AnswerMapper answerMapper = new AnswerMapperImpl();
        questionDTO.setAnswers(answerMapper.entityToDTOs(questionEntity.getAnswers()));
    }

    QuestionDTO entityToDTO(QuestionEntity questionEntity);

    List<QuestionDTO> entityToDTOs(List<QuestionEntity> questionEntities);
}
