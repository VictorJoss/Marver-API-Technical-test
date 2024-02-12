package com.test.api.marvelapi.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.api.marvelapi.persistence.integration.marvel.dto.ThumbnailDto;

public class ThumbnailMapper {

    public static ThumbnailDto toDto(JsonNode comicNode){
        if(comicNode == null){
            throw new IllegalArgumentException("El nodo json no puede ser nulo");
        }

        ThumbnailDto dto = new ThumbnailDto(
                comicNode.get("path").asText(),
                comicNode.get("extension").asText()
        );

        return dto;

    }
}
