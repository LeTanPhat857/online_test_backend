package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.AnswerEntity;
import com.sunflower.onlinetest.rest.response.AnswerDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
interface AnswerMapper {

    @AfterMapping
    default void convertIdToCode(AnswerEntity answerEntity, @MappingTarget AnswerDTO answerDTO) {
        answerDTO.setCode(CustomBase64.encode(String.valueOf(answerEntity.getId())));
    }

    AnswerDTO entityToDTO(AnswerEntity answerEntity);

    List<AnswerDTO> entityToDTOs(List<AnswerEntity> answerEntities);
}
