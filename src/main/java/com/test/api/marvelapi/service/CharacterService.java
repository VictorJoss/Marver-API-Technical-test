package com.test.api.marvelapi.service;

import com.test.api.marvelapi.dto.MyPageable;
import com.test.api.marvelapi.persistence.integration.marvel.dto.CharacterDto;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CharacterService {
    List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series);

    CharacterDto.CharacterInfoDto findInfoById(Long characterId);
}
