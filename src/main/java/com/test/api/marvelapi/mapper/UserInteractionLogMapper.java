package com.test.api.marvelapi.mapper;

import com.test.api.marvelapi.dto.GetUserInteractionLogDto;
import com.test.api.marvelapi.persistence.entity.UserInteractionLog;

public class UserInteractionLogMapper {

    public static GetUserInteractionLogDto toDto(UserInteractionLog entity){

        if(entity == null) return null;

        return new GetUserInteractionLogDto(
                entity.getId(),
                entity.getUrl(),
                entity.getHttpMethod(),
                entity.getUsername(),
                entity.getTimestamp(),
                entity.getRemoteAddress()
        );
    }
}
