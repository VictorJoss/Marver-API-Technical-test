package com.test.api.marvelapi.persistence.integration.marvel.repository;

import com.test.api.marvelapi.dto.MyPageable;
import com.test.api.marvelapi.persistence.integration.marvel.dto.ComicDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComicRepository {
    public List<ComicDto> findAll(MyPageable myPageable, Long characterId) {
        return null;
    }

    public ComicDto findById(Long comicId) {
        return null;
    }
}
