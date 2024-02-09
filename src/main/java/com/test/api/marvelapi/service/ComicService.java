package com.test.api.marvelapi.service;

import com.test.api.marvelapi.dto.MyPageable;
import com.test.api.marvelapi.persistence.integration.marvel.dto.ComicDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ComicService {
    List<ComicDto> findAll(MyPageable myPageable, Long characterId);

    ComicDto findById(Long comicId);
}
