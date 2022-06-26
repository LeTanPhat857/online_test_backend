package com.sunflower.onlinetest.rest.mapper;

import com.sunflower.onlinetest.entity.TopicEntity;
import com.sunflower.onlinetest.rest.response.TopicDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface TopicMapper {

    @Mappings({
            @Mapping(target = "createdDate", source = "topicEntity.createdDate", dateFormat = "dd/MM/yyyy hh:mm:ss")
    })

    @AfterMapping
    default void convertIdToCode(TopicEntity topicEntity, @MappingTarget TopicDTO topicDTO) {
        topicDTO.setCode(CustomBase64.encode(String.valueOf(topicEntity.getId())));
    }

    TopicDTO entityToDTO(TopicEntity topicEntity);

    List<TopicDTO> entityToDTOs(List<TopicEntity> topics);
}
