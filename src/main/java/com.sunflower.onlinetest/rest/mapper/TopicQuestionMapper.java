package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.TopicEntity;
import com.sunflower.onlinetest.rest.response.TopicQuestionDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface TopicQuestionMapper {
    @Mappings({
            @Mapping(target = "createdDate", source = "topicEntity.createdDate", dateFormat = "dd/MM/yyyy hh:mm:ss")
    })

    @AfterMapping
    default void afterMapping(TopicEntity topicEntity, @MappingTarget TopicQuestionDTO topicQuestionDTO) {
        topicQuestionDTO.setCode(CustomBase64.encode(String.valueOf(topicEntity.getId())));

        QuestionMapper questionMapper = new QuestionMapperImpl();
        topicQuestionDTO.setQuestions(questionMapper.entityToDTOs(topicEntity.getQuestions()));
    }

    TopicQuestionDTO entityToDTO(TopicEntity topicEntity);

    List<TopicQuestionDTO> entityToDTOs(List<TopicEntity> topicEntities);
}
