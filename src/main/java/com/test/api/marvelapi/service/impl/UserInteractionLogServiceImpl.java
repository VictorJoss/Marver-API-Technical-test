package com.test.api.marvelapi.service.impl;

import com.test.api.marvelapi.dto.GetUserInteractionLogDto;
import com.test.api.marvelapi.mapper.UserInteractionLogMapper;
import com.test.api.marvelapi.persistence.repository.UserInteractionLogRepository;
import com.test.api.marvelapi.service.UserInteractionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserInteractionLogServiceImpl implements UserInteractionLogService {

    @Autowired
    private UserInteractionLogRepository userInteractionLogRepository;

    @Override
    public Page<GetUserInteractionLogDto> findAll(Pageable pageable) {
        return userInteractionLogRepository.findAll(pageable)
                .map(UserInteractionLogMapper::toDto);
    }

    @Override
    public Page<GetUserInteractionLogDto> findByUsername(Pageable pageable, String username) {
        return userInteractionLogRepository.findByUsername(pageable, username)
                .map(UserInteractionLogMapper::toDto);
    }
}
