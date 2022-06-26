package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.AnswerEntity;
import com.sunflower.onlinetest.rest.response.DoExamAnswerDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface DoExamAnswerMapper {

    @AfterMapping
    default void convertIdToCode(AnswerEntity answerEntity, @MappingTarget DoExamAnswerDTO doExamAnswerDTO) {
        doExamAnswerDTO.setCode(CustomBase64.encode(String.valueOf(answerEntity.getId())));
    }

    DoExamAnswerDTO entityToDTO(AnswerEntity answerEntity);

    List<DoExamAnswerDTO> entityToDTOs(List<AnswerEntity> answerEntities);
}
