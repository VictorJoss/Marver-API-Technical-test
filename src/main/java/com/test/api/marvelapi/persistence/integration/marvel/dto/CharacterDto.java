package com.test.api.marvelapi.persistence.integration.marvel.dto;

public record CharacterDto(
        Long id, String name, String description, String modified, String resourceURI){

    public record CharacterInfoDto(
            String imgPath,
            String description
    ){}
}
