package com.test.api.marvelapi.service.impl;

import com.test.api.marvelapi.dto.MyPageable;
import com.test.api.marvelapi.persistence.integration.marvel.dto.CharacterDto;
import com.test.api.marvelapi.persistence.repository.CharacterRepository;
import com.test.api.marvelapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Override
    public List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
        return characterRepository.findAll(pageable, name, comics, series);
    }

    @Override
    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        return characterRepository.findInfoById(characterId);
    }
}