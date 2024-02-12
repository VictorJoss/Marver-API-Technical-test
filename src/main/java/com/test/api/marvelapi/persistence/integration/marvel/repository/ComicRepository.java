package com.test.api.marvelapi.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.api.marvelapi.dto.MyPageable;
import com.test.api.marvelapi.persistence.integration.marvel.MarvelApiConfig;
import com.test.api.marvelapi.persistence.integration.marvel.dto.ComicDto;
import com.test.api.marvelapi.persistence.integration.marvel.mapper.ComicMapper;
import com.test.api.marvelapi.service.HttpClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Repository
public class ComicRepository {

    @Autowired
    private MarvelApiConfig marvelApiConfig;

    @Autowired
    private HttpClientService httpClientService;

    @Value("${marvel.api.base-path}")
    private String basePath;
    private String comicPath;

    @PostConstruct
    private void setPath(){
        this.comicPath = basePath.concat("/").concat("comics");
    }

    public List<ComicDto> findAll(MyPageable myPageable, Long characterId) {

        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(myPageable, characterId);

        JsonNode response = httpClientService.doGet(comicPath, marvelQueryParams, JsonNode.class);

        return ComicMapper.toDtoList(response);
    }

    private Map<String, String> getQueryParamsForFindAll(MyPageable pageable, Long characterId) {

        Map<String, String> marvelQueryParams = marvelApiConfig.getAuthenticationQueryParams();

        marvelQueryParams.put("offset", Long.toString(pageable.offset()));
        marvelQueryParams.put("limit", Long.toString(pageable.limit()));

        if(characterId != null && characterId > 0){
            marvelQueryParams.put("characters", Long.toString(characterId));
        }

        return marvelQueryParams;
    }

    public ComicDto findById(Long comicId) {

        Map<String, String> marvelQueryParams = marvelApiConfig.getAuthenticationQueryParams();

        String finalUrl = comicPath.concat("/").concat(comicId.toString());

        JsonNode response = httpClientService.doGet(comicPath, marvelQueryParams, JsonNode.class);

        return ComicMapper.toDtoList(response).get(0);
    }
}
